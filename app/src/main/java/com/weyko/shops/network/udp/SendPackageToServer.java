package com.weyko.shops.network.udp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;

import com.alibaba.fastjson.JSONObject;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseManager;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static com.weyko.shops.config.UDPConfig.BUFF_SIZE;
import static com.weyko.shops.config.UDPConfig.SERVER_IP;
import static com.weyko.shops.config.UDPConfig.SERVER_PORT;

/**
 * Description: 发送报文到服务器
 * Created  by: weyko on 2017/6/23.
 */

public class SendPackageToServer<T> implements BaseManager{
    private static SendPackageToServer instance;
    private static final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();//创建线程队列
//    private ExecutorService service= new ThreadPoolExecutor(3, 10,60L, TimeUnit.SECONDS, queue);//运行线程数为3，最大任务队列10，生命周期为60秒
    private ExecutorService service= Executors.newCachedThreadPool();//运行线程数为3，最大任务队列10，生命周期为60秒
    private SparseArray<DatagramSocket> datagramSockets;
    private SparseArray<SendRunnable> sendRunnables;
    public SendPackageToServer() {
        datagramSockets=new SparseArray<>();
        sendRunnables=new SparseArray<>();
    }
    public static SendPackageToServer getInstance(){
        if(instance==null){
            synchronized (SendPackageToServer.class){
                if(instance==null)instance=new SendPackageToServer();
            }
        }
        return instance;
    }
    public void  sendData(FragmentActivity activity, String sendContent, Class<T> clzz, OnSendDataListener onSendDataListener){
        ShowUtil.d("weyko","sendData--->");
        service.execute(new SendRunnable(activity,sendContent,clzz,onSendDataListener));
    }
    private <T>T send(FragmentActivity activity,int instruct, String sendContent, Class<T> clzz, OnSendDataListener onSendDataListener){
        ShowUtil.d("weyko","sendContent="+sendContent);
        T result=null;
        DatagramSocket socket=null;
        try {
            // 创建发送方的套接字对象
            socket = new DatagramSocket();
            socket.setReuseAddress(true);
            byte[] buf = sendContent.getBytes(UDPConfig.BUFF_FORMAT);
            // 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
            DatagramPacket  packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(SERVER_IP), SERVER_PORT);
            ShowUtil.d("weyko", "InetAddress=" + InetAddress.getByName(SERVER_IP));
            // 从此套接字发送数据报包
            socket.send(packet);
            result = doReciveInfo(socket, clzz,instruct);
            // 关闭此数据报套接字。
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(socket!=null){
                socket.close();
            }
            ShowUtil.d("weyko","finally--->sendRunnables="+(sendRunnables.get(instruct)==null));
            if(sendRunnables.get(instruct)!=null) {
                onSendDataListener=sendRunnables.get(instruct).onSendDataListener;
            }
            ShowUtil.d("weyko","finally--->sendContent="+sendContent+" onSendDataListener--->"+(onSendDataListener==null));
            // 接收，接收者返回的数据
            if(onSendDataListener!=null){
                if(activity!=null){
                    sendRunnables.delete(instruct);
                    SendRunnable runnable=new SendRunnable(result,onSendDataListener);
                    runnable.setIsResult();
                    activity.runOnUiThread(runnable);
                }
            }
            datagramSockets.delete(instruct);
        }
        return result;
    }
    public class SendRunnable<T> implements Runnable{
        private FragmentActivity activity;
        private String sendContent;
        private Class<T> clzz;
        private OnSendDataListener onSendDataListener;
        private T result;
        private boolean isResultModel=false;
        public SendRunnable(FragmentActivity activity,String sendContent, Class<T> clzz, OnSendDataListener onSendDataListener) {
            this.activity=activity;
            this.sendContent = sendContent;
            this.clzz = clzz;
            this.onSendDataListener = onSendDataListener;
        }

        public  SendRunnable(T result, OnSendDataListener onSendDataListener) {
            this.result = result;
            this.onSendDataListener = onSendDataListener;
        }
        public void setIsResult(){
            isResultModel=true;
        }
        @Override
        public void run() {
            if(isResultModel){
                if(result==null&&onSendDataListener!=null){
                    Context context = BaseApplication.getInstance();
                    if(!SaveDataUtil.getInstance().getBoolean("CheckVersion",true)){
                        ShowUtil.showToast(context, context.getString(R.string.server_string_null));
                        SaveDataUtil.getInstance().getBoolean("CheckVersion",false);
                    }
                }
                if(onSendDataListener!=null)
                   onSendDataListener.onSendBackResult(result);
            }else{
                JSONObject obj = (JSONObject) JSONObject.parse(sendContent);
                int instruct=obj.getIntValue("instruct");
                ShowUtil.d("SendRunnable-------->instruct="+instruct);
                sendRunnables.put(instruct,this);
                send(activity,instruct,sendContent,clzz,onSendDataListener);
            }
        }
    }
    /**
     * 处理发送报文之后返回的数据
     * @param socket
     */
    private <T> T doReciveInfo(DatagramSocket socket,Class<T>clzz,int instruct) throws IOException {
        if(socket==null)return null;
        byte []buf=new byte[BUFF_SIZE];
        ShowUtil.d("weyko"," doReciveInfo------>");
        DatagramPacket packet=new DatagramPacket(buf,buf.length);
        T result=null;
        socket.setSoTimeout(UDPConfig.TIME_OUT);
        datagramSockets.put(instruct,socket);
        socket.receive(packet);
        byte[] data = packet.getData();
        result = ConvertTool.getObject(data, clzz);
        ShowUtil.d("weyko","doReciveInfo------>"+packet.getAddress()+" result="+result.toString());
        return result;
    }

    @Override
    public void onDestory() {
        if(service!=null){
            service.shutdown();
            service=null;
        }
        if(instance!=null){
            instance=null;
        }
    }
    public interface OnSendDataListener{
        public void onSendBackResult(Object result);
    }
    public void cancal(int instruct){
        if(datagramSockets!=null){
            SendRunnable sendRunnable = sendRunnables.get(instruct);
            if(sendRunnable!=null){
                ShowUtil.d("cancal-------->sendRunnable");
                sendRunnable.onSendDataListener=null;
            }
            ShowUtil.d("cancal-------->instruct="+instruct);
            DatagramSocket datagramSocket = datagramSockets.get(instruct);
            if(datagramSocket!=null){
                ShowUtil.d("cancal-------->disconnect");
                datagramSocket.disconnect();
            }
            datagramSockets.delete(instruct);
        }
    }
}
