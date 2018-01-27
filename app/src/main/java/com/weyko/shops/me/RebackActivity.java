package com.weyko.shops.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.databinding.ActivityRebackBinding;
import com.weyko.shops.manager.CheckManager;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;

/**
 * 意见反馈
 * Created by zhong.xiwei on 2017/8/9.
 */

public class RebackActivity extends BaseActivity<ActivityRebackBinding> {
    private CheckManager checkManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_reback;
    }

    @Override
    protected void initData() {
        showBack();
        showMenu(getString(R.string.reback_submit), new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                submitReback();
            }
        });
        checkManager=new CheckManager();
    }

    private void submitReback() {
        if(checkManager.checkIsRebackable(this,binding)){
            ShowUtil.showToast(this,getString(R.string.reback_submit_toast));
            finish();
        }
    }
    @Override
    protected boolean onBackIntercept() {
        finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.set_reback);
    }
}
