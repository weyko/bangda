package com.weyko.shops.task.send;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseBean;
import com.weyko.shops.config.Constant;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityTaskinfoBinding;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.task.get.GetTaskBean;
import com.weyko.shops.task.get.RequestPayBean;
import com.weyko.shops.task.get.TaskInfoBean;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;
import com.weyko.shops.view.OnScrollChangerScrollView;
import com.weyko.shops.view.TaskStatueView;

/**
 * Description: 任务详情
 * Created  by: weyko on 2017/6/28.
 */

public class TaskInfoActivity extends BaseActivity<ActivityTaskinfoBinding> {
    private TaskInfoBean.TaskInfoData infoData;
    private static int scrollPosition=0;
    private int margin=0;//按钮底部以下高度
    private String taskId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
        margin=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin)+ CommonUtils.dip2px(this,8);//按钮的margin+设置输入框跟软键盘的高度(避免紧挨软键盘)
    }
    public static void intoPage(FragmentActivity activity,String taskId){
        Intent intent=new Intent(activity, TaskInfoActivity.class);
        intent.putExtra(Constant.KEY_TASK_ID,taskId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right,0);
    }
    @Override
    protected int setContent() {
        return R.layout.activity_taskinfo;
    }
    @Override
    protected void initData() {
        showBack();
        taskId=getIntent().getStringExtra(Constant.KEY_TASK_ID);
        if(!TextUtils.isEmpty(taskId)){
            getDataById(taskId);
        }else{
            TaskInfoBean infoBean = (TaskInfoBean) getIntent().getSerializableExtra(Constant.KEY_TASK_INFO);
            if(infoBean!=null){
                infoData=infoBean.getData();
                setBindingData();
            }
        }
        boolean isNewMsg=getIntent().getBooleanExtra(Constant.KEY_TASK_INFO_NEW, false);
        if(isNewMsg){
            SaveDataUtil.getInstance().putBoolean(Constant.KEY_TASK_INFO_NEW,false);
        }
        binding.tvGetTask.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(infoData==null)return;
                doByState(infoData.getTaskState(),true);
            }
        });
        if(binding.tvGetTask.getVisibility()==View.VISIBLE) {
            binding.tvGetTask.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int dot = BaseApplication.getInstance().getScreenHeight() - binding.tvGetTask.getBottom();
                    if (dot > BaseApplication.getInstance().getScreenHeight() / 4) {//当高度变化超过1/4，就当弹出软键盘
                        binding.sviewTaskinfo.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.sviewTaskinfo.scrollTo(0, scrollPosition + binding.tvGetTask.getHeight() + margin);
                            }
                        }, 200);
                    }
                }
            });
            binding.sviewTaskinfo.setOnScrollViewChangerListener(new OnScrollChangerScrollView.OnScrollViewChangerListener() {
                @Override
                public void onScrollChanged(int top, int oldTop) {
                    scrollPosition = top;
                }
            });
        }
    }

    /**
     * 获取任务详情
     * @param taskId
     */
    private void getDataById(String taskId) {
        TaskInfoBean.TaskInfoSubmit submit=new TaskInfoBean.TaskInfoSubmit();
        submit.setInstruct(UDPConfig.ACTION_TASK_INFO);
        submit.setTaskID(taskId);
        submit.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(submit), TaskInfoBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    TaskInfoBean bean= (TaskInfoBean) result;
                    infoData=bean.getData();
                    setBindingData();
                }
            }
        });
    }

    private void setBindingData() {
        binding.setBean(infoData);
        doByState(infoData.getTaskState(),false);
    }

    /**
     * 根据状态执行操作
     * @param taskState
     * @param isClick
     */
    private void doByState(int taskState,boolean isClick){
        if(infoData==null)return;
        infoData.setTaskState(taskState);
        int showTxt=0;
        boolean isSelf=SaveDataUtil.getInstance().getSSOUserId().equals(infoData.getIssueUserId());//是否为自己
        boolean isHide=false;
        switch (taskState){
            case TaskStatueView.STATUE_SENED:
                if(isClick){
                    doGetTask();
                }else {
                    showTxt = isSelf ? R.string.task_sended : R.string.task_get;
                    if (isSelf) binding.tvGetTask.setEnabled(false);
                }
                break;
            case TaskStatueView.STATUE_GOT:
                if(isClick){
                    doRequestPay();
                }else{
                    showTxt = isSelf ? R.string.task_doing : R.string.task_notify_pay;
                    if(isSelf){
                        binding.tvGetTask.setEnabled(false);
                    }else{
                        binding.llMoneyTaskinfo.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case TaskStatueView.STATUE_PAY:
                if(isClick){
                    doPay();
                }else {
                    showTxt = isSelf ? R.string.task_agree_pay : R.string.task_paying;
                    if (!isSelf) binding.tvGetTask.setEnabled(false);
                }
                break;
            case TaskStatueView.STATUE_COMPLETE:
                showTxt=R.string.task_complete;
                isHide=true;
                break;
            default:
                showTxt=R.string.task_get;
                isHide=true;
                break;
        }
       if(!isClick){
           binding.tsvTaskinfo.setTaskStatue(taskState);
           binding.tvGetTask.setText(showTxt);
           binding.tvGetTask.setVisibility(isHide?View.GONE:View.VISIBLE);
       }
    }

    /**
     * 执行付款
     */
    private void doPay() {
        RequestPayBean requestBean = getRequestPayBean(UDPConfig.ACTION_AGREE_PAY);
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(requestBean), BaseBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    BaseBean baseBean= (BaseBean) result;
                    ShowUtil.showToast(TaskInfoActivity.this,baseBean.getMsg());
                    if(baseBean.isOk()){
                        doByState(TaskStatueView.STATUE_COMPLETE,false);
                    }
                }
            }
        });
    }

    /**
     * 请求付款
     */
    private void doRequestPay() {
        String taskMoneyStr = binding.etGoodsMoneyTaskinfo.getText().toString();
        if(!TextUtils.isEmpty(taskMoneyStr)){
            infoData.setTaskMoney(Double.valueOf(taskMoneyStr));
        }
        infoData.setRecvUserId(SaveDataUtil.getInstance().getSSOUserId());
        infoData.setRecvPhone(infoData.getRecvUserId());
        RequestPayBean requestBean = getRequestPayBean(UDPConfig.ACTION_REQUEST_PAY);
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(requestBean), BaseBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    BaseBean baseBean= (BaseBean) result;
                    ShowUtil.showToast(TaskInfoActivity.this,baseBean.getMsg());
                    if(baseBean.isOk()){
                        binding.llMoneyTaskinfo.setVisibility(View.GONE);
                        doByState(TaskStatueView.STATUE_PAY,false);
                        ShowUtil.hideSoftWindow(binding.etGoodsMoneyTaskinfo.getContext(),binding.etGoodsMoneyTaskinfo);
                    }
                }
            }
        });
    }

    /**
     * 获取请求付款对象
     * @return
     */
    private RequestPayBean getRequestPayBean(int instruct) {
        RequestPayBean requestBean=new RequestPayBean();
        requestBean.setInstruct(instruct);
        requestBean.setIssueUserId(infoData.getIssueUserId());
        requestBean.setTaskId(infoData.getTaskId());
        requestBean.setTaskName(infoData.getTaskName());
        requestBean.setTaskMoney(infoData.getTaskMoney());
        requestBean.setMoneyReward(infoData.getMoneyReward());
        requestBean.setTotalMoney(requestBean.getTaskMoney()+requestBean.getMoneyReward());
        requestBean.setRecvUserId(infoData.getRecvUserId());
        requestBean.setRecvPhone(infoData.getRecvPhone());
        return requestBean;
    }

    /**
     * 发起接单请求
     */
    private void doGetTask() {
        GetTaskBean.GetTaskSubmit submit=new GetTaskBean.GetTaskSubmit();
        TaskInfoBean.TaskInfoData bean = binding.getBean();
        if(bean==null)return;
        submit.setInstruct(UDPConfig.ACTION_GOT_TASK);
        submit.setTaskName(bean.getTaskName());
        submit.setIssueUserId(bean.getIssueUserId());
        submit.setRecvPhone(SaveDataUtil.getInstance().getSSOUserId());
        submit.setRecvUserId(submit.getRecvPhone());
        submit.setTaskId(bean.getTaskId());
        SendPackageToServer.getInstance().sendData(TaskInfoActivity.this, ConvertTool.toJsonStr(submit), GetTaskBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    GetTaskBean getResult= (GetTaskBean) result;
                    ShowUtil.showToast(TaskInfoActivity.this,getResult.getMsg());
                    if(getResult!=null){
                        doByState(TaskStatueView.STATUE_GOT,false);
                    }
                }
            }
        });
    }

    @Override
    protected boolean onBackIntercept() {
        finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.task_info_title);
    }
}
