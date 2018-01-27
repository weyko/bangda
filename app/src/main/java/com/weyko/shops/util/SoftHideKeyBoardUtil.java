package com.weyko.shops.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.weyko.shops.MainActivity;

/**
 * Description: 软键盘管理工具类
 * 备注：外层scrollview不要设置padding
 *       AndroidManifest 里面只需设置属性隐藏软键盘就好了  android:windowSoftInputMode="stateHidden"
 * Created  by: weyko on 2017/6/20.
 */

public class SoftHideKeyBoardUtil {

    public static void assistActivity(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new SoftHideKeyBoardUtil(activity);
        }
    }
    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    public SoftHideKeyBoardUtil(Activity activity) {
        //找到activity最外层布局控件，它其实是一个decorview
        FrameLayout frameLayout= (FrameLayout) activity.findViewById(android.R.id.content);
        //获取setContent设置的view
        mChildOfContent=frameLayout.getChildAt(0);
        //监听布局的变化，用于监听软键盘
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        //获取activity的布局属性
        frameLayoutParams= (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }
    /**
     * 重绘布局
     */
    private void possiblyResizeChildOfContent() {
        //获取当前页面的可用高度(键盘弹起后，会减少软键盘高度)
        int usableHeightNow=computeUsableHeight();
        //可用高度发生变化
        if(usableHeightNow!=usableHeightPrevious){
            //获取activity在当前页面的显示高度
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            //计算高度变化
            int heightDifference=usableHeightSansKeyboard-usableHeightNow;
            int height = MainActivity.titleHeight;//需要再加上底部导航栏的高度，不然软键盘弹出的时候会顶上去
            ShowUtil.d("weyko","heightDifference="+heightDifference+" usableHeightSansKeyboard="+usableHeightSansKeyboard+" height="+height+" stateBarHeight=");
            if(heightDifference>usableHeightSansKeyboard/4){//判断软键盘是否弹出
                frameLayoutParams.height=usableHeightSansKeyboard-heightDifference+height;//重新计算页面高度=显示高度-软键盘高度
                ShowUtil.d("weyko","frameLayoutParams.height1="+frameLayoutParams.height);
            }else{
                frameLayoutParams.height=usableHeightNow;//软键盘收起时，和可用高度一样
                ShowUtil.d("weyko","frameLayoutParams.height2="+frameLayoutParams.height);
            }
            mChildOfContent.requestLayout();//重绘布局
            usableHeightPrevious=usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect rect=new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(rect);
        //全屏模式下直接返回rect.bottom即可(rect.top其实就是状态栏的高度)
        return (rect.bottom-rect.top);
    }
}
