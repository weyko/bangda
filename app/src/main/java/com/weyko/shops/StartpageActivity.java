package com.weyko.shops;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.databinding.ActivityStartpageBinding;
import com.weyko.shops.manager.CheckManager;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ThemeUtil;

/**
 * Description:启动页面
 * Created  by: weyko on 2017/5/27.
 */

public class StartpageActivity extends BaseActivity<ActivityStartpageBinding> {
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_startpage;
    }
    @Override
    protected void initData() {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        },2*1000);
        binding.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMainActivity();
            }
        });
    }

    @Override
    protected boolean onBackIntercept() {
        return false;
    }

    @Override
    protected String headerTitle() {
        return null;
    }

    public void onClick(View view){
        handler.removeCallbacksAndMessages(null);
        toMainActivity();
    }
    private boolean isIn=false;
    private void toMainActivity() {
        if (isIn) {
            return;
        }
        CheckManager configUtil=new CheckManager();
        configUtil.checkFirstLogin(this);
        isIn = true;
    }
}
