package com.weyko.shops.util;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.weyko.shops.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description: 状态栏设置工具
 * Created  by: weyko on 2016/11/16.
 */

public class ThemeUtil {
    private static ThemeUtil instance;
    private boolean isDarkTheme=false;
    public ThemeUtil() {
    }
    public final static ThemeUtil getInstance(){
        if(instance==null){
            synchronized (ThemeUtil.class){
                if(instance==null)instance=new ThemeUtil();
            }
        }
        return instance;
    }
    public Window setWindow(Activity activity) {
        if(activity==null)return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.requestFeature(Window.FEATURE_NO_TITLE);
            boolean isSystemMode=false;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                isSystemMode=true;
//                window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            }else{
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if(!isSystemMode) {//非系统模式则使用第三方的设置方式
//                boolean isSetEnable = checkSetStatusBarEnable(window);
                window.setStatusBarColor(/*isSetEnable ? Color.TRANSPARENT :*/ activity.getResources().getColor(R.color.colorPrimary));
            }
            return window;
        }
        return null;
    }
    /**
     * 检查当前手机是否可以设置深色状态栏[目前只开放魅族和小米的]
     * @param window 需要设置的窗口
     * @return boolean 可以设置返回true
     */
    private boolean  checkSetStatusBarEnable(Window window){
        boolean isEnable=false;
        if(FlymeSetStatusBarLightMode(window,isDarkTheme)){
            isEnable=true;
        }else if(MIUISetStatusBarLightMode(window,isDarkTheme)){
            isEnable=true;
        }
        return  isEnable;
    }
    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    private boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }
    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    private boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;
            }catch (Exception e){

            }
        }
        return result;
    }
    public void smoothSwitchScreen(FragmentActivity activity) {
        // 5.0以上修复了此bug
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup rootView = ((ViewGroup) activity.findViewById(android.R.id.content));
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
            ShowUtil.d("statusBarHeight="+statusBarHeight);
            rootView.setPadding(0, statusBarHeight, 0, 0);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
