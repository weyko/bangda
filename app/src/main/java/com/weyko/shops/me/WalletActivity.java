package com.weyko.shops.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.CommonWebActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.bean.WalletBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityWalletBinding;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.pay.PayTypeDialog;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;

/**
 * Description:钱包首页
 * Created  by: weyko on 2017/7/17.
 */

public class WalletActivity extends BaseActivity<ActivityWalletBinding>{
    private PayTypeDialog payTypeDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initData() {
        showBack();
        showMenu(getString(R.string.wallet_withdraw_helper), new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                CommonWebActivity.openWeb(WalletActivity.this,"invite_role",getString(R.string.wallet_withdraw_helper));
            }
        });
        getWalletData();
        int comingCount = SaveDataUtil.getInstance().getInt("comingCount");
        if(SaveDataUtil.getInstance().getBoolean("isWalletReaded",false)||comingCount>2) {
            binding.tvToastWallet.setVisibility(View.GONE);
            binding.tvToastWallet.setOnClickListener(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    SaveDataUtil.getInstance().putBoolean("isWalletReaded", true);
                    binding.tvToastWallet.setVisibility(View.GONE);
                }
            });
        }
        SaveDataUtil.getInstance().putInt("comingCount",comingCount+1);
        binding.tvBanlanceWallet.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent=new Intent(WalletActivity.this,WalletRecordActivity.class);
                intent.putExtra("title",getString(R.string.record_wallet));
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,0);
            }
        });
        binding.tvIntegralWallet.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent=new Intent(WalletActivity.this,IntegralRecordActivity.class);
                intent.putExtra("title",getString(R.string.record_integral));
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,0);
            }
        });
        binding.tvWithdraw.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(payTypeDialog==null){
                    payTypeDialog=new PayTypeDialog(WalletActivity.this);
                }
                if(!payTypeDialog.isShowing())
                   payTypeDialog.show();
            }
        });
    }

    /**
     * 获取钱包数据
     */
    private void getWalletData(){
        WalletBean.WalletSubmit walletBean=new WalletBean.WalletSubmit();
        walletBean.setInstruct(UDPConfig.ACTION_MY_WALLET);
        walletBean.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(walletBean), WalletBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result instanceof WalletBean){
                    WalletBean walletBean= (WalletBean) result;
                    ShowUtil.showToast(WalletActivity.this,walletBean.getMsg());
                    if(walletBean.isOk()){
                        setWalletData(walletBean.getData());
                    }
                }
            }
        });
    }

    /**
     * 设置钱包
     * @param data
     */
    private void setWalletData(WalletBean.WalletData data) {
        binding.tvBanlanceWallet.setText(binding.tvBanlanceWallet.getTag().toString()+"\n"+data.getBalanec());
        binding.tvIntegralWallet.setText(binding.tvIntegralWallet.getTag().toString()+"\n"+data.getIntegrals());
    }

    @Override
    protected boolean onBackIntercept() {
        SendPackageToServer.getInstance().cancal(UDPConfig.ACTION_MY_WALLET);
        this.finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.me_wallet);
    }
}
