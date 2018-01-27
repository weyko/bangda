package com.weyko.shops.me;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;

import com.weyko.shops.CommonWebActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.bean.InviteCodeBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityInviteBinding;
import com.weyko.shops.manager.PermissionManager;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.share.ShareUtils;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;

import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Description:
 * Created  by: weyko on 2017/7/17.
 */

public class InviteFriendActivity extends BaseActivity<ActivityInviteBinding> implements View.OnClickListener {
    private ShareUtils shareUtils;
    private String title;
    private String content;
    private String imagUrl;
    private String url;
    private PermissionManager permissionManager;
    private String regetHint;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initData() {
        showBack();
        showMenu(getString(R.string.invite_helper), new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                CommonWebActivity.openWeb(InviteFriendActivity.this,"invite_role",getString(R.string.invite_helper));
            }
        });
        initShare();
        regetHint=getString(R.string.invite_get_error_hint);
        binding.ivQqInvite.setOnClickListener(this);
        binding.ivWexinInvite.setOnClickListener(this);
        binding.ivMsgInvite.setOnClickListener(this);
        getRecode();
        binding.tvRecodeInvite.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                getRecode();
            }
        });
    }

    /**
     * 获取邀请码
     */
    private void getRecode() {
        InviteCodeBean.InviteCodeSubmit inviteCodeSubmit=new InviteCodeBean.InviteCodeSubmit();
        inviteCodeSubmit.setInstruct(UDPConfig.ACTION_INVITE_INFO);
        inviteCodeSubmit.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(inviteCodeSubmit), InviteCodeBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    InviteCodeBean bean= (InviteCodeBean) result;
                    String recode=regetHint;
                    if(bean.isOk()){
                        recode=bean.getData().getInviteCode();
                    }else{
                        ShowUtil.showToast(InviteFriendActivity.this,bean.getMsg());
                    }
                    setRecodeView(recode,!bean.isOk());
                }else{
                    setRecodeView(regetHint,true);
                }
            }
        });
    }

    /**
     * 设置邀请码视图
     * @param showText
     * @param isEnable
     */
    private void setRecodeView(String showText,boolean isEnable){
        int dimensionPixelSize = getResources().getDimensionPixelSize(isEnable ? R.dimen.text_size_1 : R.dimen.text_size_0);
        ShowUtil.d("dimensionPixelSize="+dimensionPixelSize);
        binding.tvRecodeInvite.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimensionPixelSize);
        binding.tvRecodeInvite.setText(showText);
        binding.tvRecodeInvite.setEnabled(isEnable);
    }
    @Override
    protected boolean onBackIntercept() {
        this.finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.me_share);
    }
    @Override
    public void onClick(View view) {
        if(regetHint.equals(binding.tvRecodeInvite.getText().toString())){
            ShowUtil.showToast(this,getString(R.string.invite_get_error_toast));
            return;
        }
        switch (view.getId()){
            case R.id.iv_qq_invite:
                shareUtils.shareQqOrZone(QQ.NAME,title,content,imagUrl,url);
                break;
            case R.id.iv_wexin_invite:
                permissionManager.checkPermisson(this, PermissionManager.RequestPermisson.PERMISSION_SDCARD_WRITE, new PermissionManager.OnPermissionListener() {
                    @Override
                    public void onPermissionCheckResult(boolean isAllow) {
                        if(isAllow){
                            BitmapDrawable drawable= (BitmapDrawable) getResources().getDrawable(R.mipmap.app_logo);
                            shareUtils.shareWeChatFriends(Wechat.NAME,title,content,drawable.getBitmap(),url);
                        }
                    }
                });

                break;
            case R.id.iv_msg_invite:
                permissionManager.checkPermisson(this, PermissionManager.RequestPermisson.PERMISSION_SMS, new PermissionManager.OnPermissionListener() {
                    @Override
                    public void onPermissionCheckResult(boolean isAllow) {
                        if(isAllow){
                            handler.sendEmptyMessage(0);
                        }
                    }
                });
                break;
        }
    }
    private void initShare(){
        shareUtils=new ShareUtils();
        permissionManager=new PermissionManager();
        title="邀请好友";
        content=getString(R.string.invite_toast);
        imagUrl="http://img3.redocn.com/tupian/20150408/shucaihelvyezhuangshideshiliangbiankuang_4036044.jpg";
        url="https://www.baidu.com";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                shareUtils.doSendSMSTo(InviteFriendActivity.this,content);
                return;
            }
            permissionManager.checkPermisson(InviteFriendActivity.this, PermissionManager.RequestPermisson.PERMISSION_SMS_READ, new PermissionManager.OnPermissionListener() {
                @Override
                public void onPermissionCheckResult(boolean isAllow) {
                    if(isAllow){
                        handler.sendEmptyMessage(1);
                    }
                }
            });
        }
    };
}
