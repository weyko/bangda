package com.weyko.shops.manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.weyko.shops.MainActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.bean.HeartBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.task.get.TaskInfoBean;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.weyko.shops.config.UDPConfig.BUFF_SIZE;
import static com.weyko.shops.config.UDPConfig.SERVER_IP;
import static com.weyko.shops.config.UDPConfig.SERVER_PORT;

/**
 * Description:
 * Created  by: weyko on 2017/7/12.
 */

public class HearManager extends Service{
    private ExecutorService service= Executors.newCachedThreadPool();
    private static  boolean isStop=false;
    private static long currentTimeHeart=0;
    private DatagramSocket socket=null;
    private DatagramPacket packet=null;
    private void sendData(String sendContent){
        try {
            if(socket==null) {
                socket = new DatagramSocket();
                socket.setReuseAddress(true);
            }
            byte[] buf = sendContent.getBytes(UDPConfig.BUFF_FORMAT);
            // 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
            if(packet==null)
               packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(SERVER_IP), SERVER_PORT);
            else{
                packet.setData(buf);
            }
            ShowUtil.d("weyko", "HearManager---->ip=" + InetAddress.getByName(SERVER_IP)+" sendContent="+sendContent);
            // 从此套接字发送数据报包
            socket.send(packet);
            TaskInfoBean recive = doReciveInfo(socket, TaskInfoBean.class);
            if(recive!=null&&recive.getData()!=null){
                NotifyManager.getInstance().setNotifyDatas(recive.getData().getTaskName(),recive);
            }
            ShowUtil.d("weyko", "HearManager---->taskInfoBean=" + ConvertTool.toString(recive));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理发送报文之后返回的数据
     * @param socket
     */
    private <T> T doReciveInfo(DatagramSocket socket,Class<T>clzz) throws IOException {
        if(socket==null)return null;
        byte []buf=new byte[BUFF_SIZE];
        ShowUtil.d("weyko","HearManager-doReciveInfo------>");
        DatagramPacket packet=new DatagramPacket(buf,buf.length);
        T result=null;
        socket.setSoTimeout(UDPConfig.TIME_OUT);
        socket.receive(packet);
        byte[] data = packet.getData();
        result = ConvertTool.getObject(data, clzz);
        ShowUtil.d("weyko","HearManager--doReciveInfo------>"+" result="+result.toString());
        return result;
    }
    public void doneHeart(){
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (!isStop){
                    long nowTime=System.currentTimeMillis();
                    if(nowTime-currentTimeHeart< UDPConfig.TIME_RECIVE_HEART){//控制心跳时间，每隔一段时间获取一次
                        continue;
                    }
                    ShowUtil.d("doneHeart----------------->");
                    currentTimeHeart=nowTime;
                    getServerData();
                }
            }
        });
    }

    /**
     * 获取服务器数据
     */
    private void getServerData() {
        HeartBean baseBean=new HeartBean();
        baseBean.setUserType(1);
        baseBean.setUserId(SaveDataUtil.getInstance().getSSOUserId());

        baseBean.setInstruct(UDPConfig.ACTION_HEART_TASK_NEW);
        sendData(ConvertTool.toJsonStr(baseBean));
        baseBean.setInstruct(UDPConfig.ACTION_HEART_TASK_PAY);
        sendData(ConvertTool.toJsonStr(baseBean));
        baseBean.setInstruct(UDPConfig.ACTION_HEART_TASK_COMPLETE);
        sendData(ConvertTool.toJsonStr(baseBean));
    }

    private void onDestory(){
        currentTimeHeart=0;
        this.stopSelf();
        if(socket!=null)socket.close();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(intent==null)return;
        boolean isStop=intent.getBooleanExtra("isStop",false);
        this.isStop=isStop;
        if(!isStop){
            doneHeart();
        }else {
            onDestory();
        }
    }
    public static void doHeart(boolean isStop){
        FragmentActivity activity=BaseApplication.getInstance().getActivity(MainActivity.class.getSimpleName());
        if(activity==null)return;
        Intent intent=new Intent(activity,HearManager.class);
        intent.putExtra("isStop",isStop);
        activity.startService(intent);
    }
}
