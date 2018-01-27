package com.weyko.shops.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.weyko.shops.MainActivity;
import com.weyko.shops.R;
import com.weyko.shops.StartpageActivity;
import com.weyko.shops.databinding.ActivityBaseBinding;
import com.weyko.shops.login.LoginActivity;
import com.weyko.shops.swipebackhelper.SwipeBackHelper;
import com.weyko.shops.swipebackhelper.SwipeListener;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ShowUtil;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Description:
 * Created  by: weyko on 2017/5/27.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    public  T binding;
    public ActivityBaseBinding baseBinding;
    private CompositeSubscription compositeSubscription;
    private View view_title_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding=DataBindingUtil.setContentView(this,R.layout.activity_base);
        view_title_main=this.findViewById(R.id.view_title_main);
        binding= DataBindingUtil.inflate(getLayoutInflater(),setContent(),baseBinding.containerActivity,true);
        showTitle(headerTitle());
        baseBinding.viewTitleMain.ivBack.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                onBackIntercept();
            }
        });
        baseBinding.containerActivity.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ShowUtil.hideSoftWindow(v.getContext(),v);
            }
        });
        BaseApplication.getInstance().addActivity(this);
        initData();
        if(isFilterActivity()){
            initSwipBackHelper();
        }
    }
    protected boolean isShowAnimation = true;
    //右滑关闭
    private void initSwipBackHelper() {
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
//                .setScrimColor(Color.GRAY)//底层阴影颜色
                .setClosePercent(0.6f)//触发关闭Activity百分比
                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .addListener(new SwipeListener() {
                    @Override
                    public void onScroll(float percent, int px) {

                    }

                    @Override
                    public void onEdgeTouch() {

                    }

                    @Override
                    public void onScrollToClose() {
                        isShowAnimation = false;
                    }
                });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (isFilterActivity()) {
            SwipeBackHelper.onPostCreate(this);
        }
    }
    private boolean isFilterActivity(){
        return !(this instanceof MainActivity)
                && !(this instanceof StartpageActivity)
                && !(this instanceof LoginActivity);
    }
    @Override
    public void onBackPressed() {
        if(onBackIntercept())return;
        super.onBackPressed();
    }
    abstract protected int setContent();
    abstract protected void initData();
    abstract protected boolean onBackIntercept();
    abstract protected String headerTitle();
    public void startActivity(Class clzz,boolean needFinish){
        Intent intent=new Intent(this,clzz);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right,0);
        if(needFinish)finish();
    }
    public void startActivity(Class clzz,Bundle bundle){
        Intent intent=new Intent(this,clzz);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right,0);
    }
    public void showBack(){
        baseBinding.viewTitleMain.ivBack.setVisibility(View.VISIBLE);
    }
    public void showTitle(String title){
        boolean isEmpty = TextUtils.isEmpty(title);
        view_title_main.setVisibility(!isEmpty?View.VISIBLE:View.GONE);
        if(!isEmpty)
         baseBinding.viewTitleMain.tvTitle.setText(title);
    }
    public void showMenu(String menuTitle, View.OnClickListener onClickListener){
        baseBinding.viewTitleMain.tvMenuTitle.setText(menuTitle);
        baseBinding.viewTitleMain.tvMenuTitle.setVisibility(View.VISIBLE);
        baseBinding.viewTitleMain.tvMenuTitle.setOnClickListener(onClickListener);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getInstance().removeActivity(this.getClass().getSimpleName());
        if(compositeSubscription!=null&&compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
        if(isFilterActivity()){
            SwipeBackHelper.onDestroy(this);
        }
    }
    private View showView;
    public void setShowView(View showView){
        this.showView=showView;
    }
    public boolean isWindowShow(){
        return showView!=null&&showView.getVisibility()==View.VISIBLE;
    }
    public void hideWindow(){
        if(showView!=null)showView.setVisibility(View.GONE);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            if(showView!=null){
                if(isWindowShow()){
                    if(isFilterRect(ev,showView)){
                        return super.dispatchTouchEvent(ev);
                    }
                    hideWindow();
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /**
     * 是否为过滤控件
     * @return
     */
    private boolean isFilterRect(MotionEvent ev,View view){
        if(view==null)return false;
        Rect rect=new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.contains((int)ev.getX(),(int)ev.getY());
    }
    public void addSubscription(Subscription subscription){
        if(compositeSubscription==null){
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.out_from_right);
    }
}
