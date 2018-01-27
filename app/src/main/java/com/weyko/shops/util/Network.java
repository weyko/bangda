package com.weyko.shops.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.weyko.shops.log.LogUtil;

/**
 * 网络信息
 */
public class Network {

	private static NetworkInfo mNetworkInfo=null;
	private static long checkTime=0l;

	public enum NetType {
		None(1),
		Mobile(2),
		Wifi(4),
		Other(8);
		NetType(int value) {
			this.value = value;
		}
		public int value;
	}
	/**
	 * 获取当前网络链接类型
	 */
	public static NetType getConnectedType(Context context) {
		checkNetwork(context);
		if (mNetworkInfo != null) {
			switch (mNetworkInfo.getType()) {
				case ConnectivityManager.TYPE_WIFI :
					return NetType.Wifi;
				case ConnectivityManager.TYPE_MOBILE :
					return NetType.Mobile;
				default :
					return NetType.Other;
			}
		}
		return NetType.None;
	}

	public synchronized static boolean isNetworkConnected(Context context) {
		checkNetwork(context);
		return (mNetworkInfo!=null)?mNetworkInfo.isConnected():false;
	}

	public synchronized static boolean hasWifiNetwork(Context context) {
		checkNetwork(context);
		return (mNetworkInfo!=null)?(mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI):false;
	}

	private synchronized static void checkNetwork(Context context) {
		long curTime= System.currentTimeMillis();
		if( ((curTime-checkTime)>= 2000||mNetworkInfo==null) && context != null){
			checkTime = curTime;
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			LogUtil.i(Network.class.getSimpleName(),"----------moxian check Network----------");
		}
	}

	public synchronized static NetworkInfo getActiveNetworkInfo(Context context){
		if(mNetworkInfo==null || !mNetworkInfo.isAvailable()){
			checkTime=0l;
		}
		checkNetwork(context);
		return mNetworkInfo;
	}
}