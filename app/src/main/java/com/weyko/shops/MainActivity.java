package com.weyko.shops;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.weyko.shops.adapter.MyFragmentPagerAdapter;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.config.Constant;
import com.weyko.shops.databinding.ActivityMainBinding;
import com.weyko.shops.fragment.GetTaskFragment;
import com.weyko.shops.fragment.MeFragment;
import com.weyko.shops.fragment.SendTaskFragment;
import com.weyko.shops.manager.CacheManager;
import com.weyko.shops.manager.HearManager;
import com.weyko.shops.manager.VersionManager;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.SoftHideKeyBoardUtil;
import com.weyko.shops.util.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 首页
 * Created  by: weyko on 2017/6/1.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private SendTaskFragment sendTaskFragment;
    private MyFragmentPagerAdapter adapter;
    private List<Fragment>list;
    private int currentPosition=0;
    private int[]titles={R.string.tab_home,R.string.tab_task,R.string.tab_me};
    public static int titleHeight=0;
    private VersionManager versionManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
        SoftHideKeyBoardUtil.assistActivity(this);
        SaveDataUtil.getInstance().putBoolean(Constant.KEY_APP_FIRST,false);
        binding.rgMain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(titleHeight==0) {
                    titleHeight = binding.rgMain.getHeight();
                }
            }
        });
        versionManager=new VersionManager();
        SaveDataUtil.getInstance().putBoolean("CheckVersion",true);
        versionManager.checkVersion(this);
    }
    @Override
    protected int setContent() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {
        list=new ArrayList<>();
        sendTaskFragment = new SendTaskFragment();
        list.add(sendTaskFragment);
        list.add(new GetTaskFragment());
        list.add(new MeFragment());
        adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        binding.vpMain.setAdapter(adapter);
        binding.vpMain.setOffscreenPageLimit(3);
        binding.vpMain.setOnPageChangeListener(this);
        binding.rgMain.setOnCheckedChangeListener(this);
        HearManager.doHeart(false);
//      ReciverPackageFromServer.getInstance().doneRecive();
    }
    private long currentTime=0;
    @Override
    protected boolean onBackIntercept() {
        if(isWindowShow()){
            hideWindow();
            return true;
        }
        if(checkIsExit(currentTime)){
            finish();
            return false;
        }
        return true;
    }
    /**
     * 检查是否退出
     * @param clickTime
     * @return
     */
    public boolean checkIsExit(long clickTime){
        long nowTime=System.currentTimeMillis();
        if(nowTime-clickTime>2*1000){
            currentTime=nowTime;
            ShowUtil.showToast(BaseApplication.getInstance(),getString(R.string.main_back_toast));
            return false;
        }
        return true;
    }
    @Override
    protected String headerTitle() {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition=position;
        binding.viewTitleMain.tvTitle.setText(titles[position]);
        RadioButton childAt = (RadioButton) binding.rgMain.getChildAt(position);
        childAt.setChecked(true);
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        for(int i=0;i<3;i++){
            if(radioGroup.getChildAt(i).getId()==id){
                currentPosition=i;
            }
        }
        binding.vpMain.setCurrentItem(currentPosition);
    }

    @Override
    protected void onDestroy() {
        CacheManager.getInstance().onDestory();
        binding.vpMain.removeAllViews();
        super.onDestroy();
        if(versionManager!=null)versionManager.onDestory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        versionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
