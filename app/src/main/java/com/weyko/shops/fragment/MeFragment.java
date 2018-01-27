package com.weyko.shops.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.CommonWebActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseFragment;
import com.weyko.shops.databinding.FragmentMeBinding;
import com.weyko.shops.me.InviteFriendActivity;
import com.weyko.shops.me.MyTaskActivity;
import com.weyko.shops.me.SetActivity;
import com.weyko.shops.me.WalletActivity;

/**
 * Description:我的个人中心
 * Created  by: weyko on 2017/6/5.
 */

public class MeFragment extends BaseFragment<FragmentMeBinding> implements View.OnClickListener {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initData();
        showContentView();
    }
    private void initData() {
        binding.tvTasksMe.setOnClickListener(this);
        binding.tvSettingMe.setOnClickListener(this);
        binding.tvShareMe.setOnClickListener(this);
        binding.tvWalletMe.setOnClickListener(this);
        binding.tvLevelMe.setOnClickListener(this);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_me;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_tasks_me:
                startActivity(MyTaskActivity.class);
                break;
            case R.id.tv_share_me:
                startActivity(InviteFriendActivity.class);
                break;
            case R.id.tv_setting_me:
                startActivity(SetActivity.class);
                break;
            case R.id.tv_wallet_me:
                startActivity(WalletActivity.class);
                break;
            case R.id.tv_level_me:
                CommonWebActivity.openWeb(getActivity(),"integral_role",getString(R.string.set_integral_service));
                break;
        }
    }
}
