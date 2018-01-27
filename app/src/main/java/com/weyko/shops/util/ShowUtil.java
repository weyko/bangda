package com.weyko.shops.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseApplication;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static android.widget.Toast.makeText;
import static com.weyko.shops.config.Constant.isDebug;

public class ShowUtil {

	public static String getDoubleWithoutZeroString(double num) {
		if (num == 0) {
			return "0";
		}
		return BigDecimal.valueOf(num).stripTrailingZeros().toPlainString();
	}

	/*public static String currencyFormat(double doubleNun) {
		String str = ""+doubleNun;
		if (doubleNun==0) {
			return "0";
		}
		try {
			Format formater = null;
			if (doubleNun==(int)doubleNun) {
				formater=new DecimalFormat("#,###");
			}else {
				formater=new DecimalFormat("#,##0.00");
			}
			str = formater.format(doubleNun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}*/
	/**
	 * 屏幕枚举 ScreenEnum
	 * 
	 * @author weyko 2015年3月19日上午10:33:21 包含属性WIDTH（宽）,HEIGHT（高）,DENSITY（密度）
	 */
	public enum ScreenEnum {
		WIDTH, HEIGHT, DENSITY
	}
	private static final String TAG = "weyko";// 日志标签，过滤用

	/**
	 * 弹出提示框
	 * 
	 * @param context
	 * @param msg
	 *            提示内容
	 */
	public static void showToast(Context context, String msg) {
		if (context==null)
			return;
		setToast(Toast.makeText(context, msg, Toast.LENGTH_SHORT));
	}
	private static void setToast(Toast toast){
		toast.setGravity(Gravity.TOP,0,BaseApplication.getInstance().getScreenHeight()/4);
		toast.show();
	}
	/**
	 * 弹出提示框
	 * 
	 * @param context
	 * @param msgResId
	 *            提示内容的资源id
	 */
	public static void showToast(Context context, int msgResId) {
		if (context == null)
			return;
		String msg="";
		try{
			msg=context.getResources().getString(msgResId);
		}catch(NotFoundException e){
			e.printStackTrace();
			msg=context.getResources().getString(R.string.server_string_null);
		}
		setToast(makeText(context, msg, Toast.LENGTH_SHORT));
	}

	/**
	 * 打印日志
	 * 
	 * @param context
	 * @param msg
	 *            日志内容
	 */
	public static void log(Context context, String msg) {
		if (isDebug && context != null)
			Log.d(TAG, context.getClass().getName() + "--->" + msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.d(tag, msg);
	}
	public static void d(String msg) {
		if (isDebug)
			Log.d("weyko", msg);
	}

	/**
	 * 打印日志
	 * 
	 * @param activity
	 * @param msg
	 *            日志内容
	 */
	public static void log(Activity activity, String msg) {
		if (isDebug && activity != null)
			Log.d(TAG, activity.getClass().getName() + "--->" + msg);
	}

	/**
	 * 获取屏幕大小信息
	 * 
	 * @param context
	 * @param screenEnum
	 *            获取所需类型数据.WIDTH:宽；HEIGHT：高；DENSITY：密度
	 * @return
	 */
	public static int getScreenSize(Context context, ScreenEnum screenEnum) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		switch (screenEnum) {
		case WIDTH:
			return metrics.widthPixels;
		case HEIGHT:
			return metrics.heightPixels;
		case DENSITY:
			return metrics.densityDpi;
		}
		return 0;
	}

	/**
	 * 获取屏幕大小信息
	 * 
	 * @param activity
	 * @param screenEnum
	 *            获取所需类型数据.WIDTH:宽；HEIGHT：高；DENSITY：密度
	 * @return
	 */
	public static int getScreenSize(Activity activity, ScreenEnum screenEnum) {
		WindowManager manager = activity.getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		switch (screenEnum) {
		case WIDTH:
			return metrics.widthPixels;
		case HEIGHT:
			return metrics.heightPixels;
		case DENSITY:
			return metrics.densityDpi;
		}
		return 0;
	}

	/**
	 * 获取状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStateBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbar;
	}

	/**
	 * 针对接口请求后异常提示
	 * 
	 * @param codeStr
	 *            Bean里面的code
	 */
	public static void showResutToast(Context context, String codeStr) {

		int rInt = context.getResources().getIdentifier(codeStr, "string",
				context.getPackageName());

		if (rInt == 0) {
			rInt = R.string.server_string_null;
		}
		showToast(context, rInt);
	}

	/**
	 * 获取服务器返回的code值，
	* @Title: getResutToastString 
	* @param: Context context
	* 		  String codeStr
	* @Description: 获取密码的密码问题，但是如果是自定义问题需要不能返回其它值
	* @return String
	 */
	public static String getResutToastString(Context context, String codeStr){
		int rInt = 0;
		try {
			rInt = context.getResources().getIdentifier(codeStr, "string",
					context.getPackageName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rInt == 0 || codeStr.equals(String.valueOf(rInt))) {
			return codeStr;
		}else{
			String mResourceString = "";
			try {
				mResourceString = context.getResources().getString(rInt);
			} catch (NotFoundException e) {
				e.printStackTrace();
				return codeStr;
			}
			return mResourceString;
		}
	
	}
	/**
	 * @Title: showSoftWindow
	 * @param:
	 * @Description: 显示软键盘
	 * @return void
	 */
	public static void showSoftWindow(Context context, View view) {
		if(context==null||view==null)
			return;
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);

	}
	/**
	 * @Title: showSoftWindow
	 * @param:
	 * @Description: 显示软键盘
	 * @return void
	 */
	public static void hideSoftWindow(Context context, View view) {
		if(context==null||view==null)
			return;
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘  
	}
}
