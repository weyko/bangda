package com.weyko.shops.manager;

import android.support.v4.app.FragmentActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseBean;
import com.weyko.shops.base.BaseManager;
import com.weyko.shops.bean.LocationBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.SaveDataUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 位置管理类
 * Created  by: weyko on 2017/6/26.
 */

public class LocationManager implements BaseManager, AMapLocationListener {
    private static LocationManager instance;
    public static LocationManager getInstance(){
        if(instance==null){
            synchronized (LocationManager.class){
                if(instance==null)instance=new LocationManager();
            }
        }
        return instance;
    }
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mlocationClient;
    private AMapLocationListener aMapLocationListener;
    private FragmentActivity activity;
    private ExecutorService service= Executors.newSingleThreadExecutor();
    public void upLoadLocation(FragmentActivity activity, AMapLocationListener aMapLocationListener){
        this.activity=activity;
        getCurrentLocation(activity,aMapLocationListener);
//        service.submit(new LocationRunnable(activity,aMapLocationListener));
    }
    public class LocationRunnable implements Runnable{
        private FragmentActivity activity;
        private AMapLocationListener aMapLocationListener;

        public LocationRunnable(FragmentActivity activity, AMapLocationListener aMapLocationListener) {
            this.activity = activity;
            this.aMapLocationListener = aMapLocationListener;
        }

        @Override
        public void run() {
            getCurrentLocation(activity,aMapLocationListener);
        }
    }
    /**
     * 获取当前位置
     * @param activity
     * @param aMapLocationListener
     */
    private void getCurrentLocation(FragmentActivity activity, AMapLocationListener aMapLocationListener){
        this.activity=activity;
        this.aMapLocationListener=aMapLocationListener;
        //声明mLocationOption对象
        if(mlocationClient==null) {
            mlocationClient = new AMapLocationClient(BaseApplication.getInstance());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms，这里设置为5分钟
              mLocationOption.setInterval(5*60*1000);
            //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
            mLocationOption.setWifiScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(true);
            //设置只定位一次
            mLocationOption.setOnceLocation(false);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        }
        //启动定位
        mlocationClient.startLocation();
    }
    @Override
    public void onDestory() {
        if(mlocationClient!=null){
            mlocationClient.stopLocation();
            mlocationClient=null;
        }
        if(service!=null){
            service.shutdown();
            service=null;
        }
        instance=null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocationListener!=null){
            aMapLocationListener.onLocationChanged(aMapLocation);
        }
        if(activity==null)return;
        LocationBean locationBean=new LocationBean();
        locationBean.setInstruct(UDPConfig.ACTION_LOCATION);
        locationBean.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        locationBean.setLatitude(aMapLocation.getLatitude());
        locationBean.setLgitude(aMapLocation.getLongitude());
        SaveDataUtil.getInstance().setLatitude(aMapLocation.getLatitude());
        SaveDataUtil.getInstance().setLgitude(aMapLocation.getLongitude());
        SendPackageToServer.getInstance().sendData(activity, ConvertTool.toJsonStr(locationBean), BaseBean.class,null);
    }
}
