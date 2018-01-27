package com.weyko.shops.me;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.weyko.shops.R;
import com.weyko.shops.adapter.BaseListViewHolder;
import com.weyko.shops.adapter.MyFragmentPagerAdapter;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityMytaskBinding;
import com.weyko.shops.databinding.ItemTaskBinding;
import com.weyko.shops.fragment.MyTaskFragment;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.task.get.TaskListBean;
import com.weyko.shops.task.send.TaskInfoActivity;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ThemeUtil;
import com.weyko.shops.view.TaskStatueView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:我的任务
 * Created  by: weyko on 2017/7/31.
 */

public class MyTaskActivity extends BaseActivity<ActivityMytaskBinding> implements BaseListViewHolder.OnBindItemListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private PopupWindow popupWindow;
    private MyFragmentPagerAdapter adapter;
    private List<MyTaskFragment> list;
    private int currentPosition=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_mytask;
    }

    @Override
    protected void initData() {
        showBack();
        initPage();
        binding.rgMytask.setOnCheckedChangeListener(this);
    }

    private void initPage() {
        list=new ArrayList<>();
        int taskStates[]=new int[]{TaskStatueView.STATUE_SENED,TaskStatueView.STATUE_GOT,TaskStatueView.STATUE_PAY,TaskStatueView.STATUE_COMPLETE};
        String emptyHints[]=new String[]{getString(R.string.task_empty_sended),getString(R.string.task_empty_got),getString(R.string.task_empty_pay),getString(R.string.task_empty_complete)};
        for (int i=0;i<4;i++){
            list.add(MyTaskFragment.getInstance(taskStates[i],emptyHints[i]));
        }
        adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        binding.vpMytask.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.vpMytask.setOffscreenPageLimit(4);
        binding.vpMytask.setOnPageChangeListener(this);
        //诡异的问题，第一次进来的时候，loadList方法没有走
        handler.sendEmptyMessageDelayed(100,500);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list.get(0).onRefresh();
        }
    };
    private LinearLayout ll_menu_task;
    private void showPopwindow(){
        if(popupWindow==null){
            View view= LayoutInflater.from(this).inflate(R.layout.layout_menu_task,null);
            popupWindow=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            ll_menu_task = (LinearLayout) view.findViewById(R.id.ll_menu_task);
            LinearLayout ll_root_menu_task = (LinearLayout) view.findViewById(R.id.ll_root_menu_task);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_menu_task.getLayoutParams();
            layoutParams.topMargin= baseBinding.viewTitleMain.llTitleMain.getHeight();
            ll_menu_task.setLayoutParams(layoutParams);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setOutsideTouchable(true);
            ll_root_menu_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }
        if(!popupWindow.isShowing()){
            popupWindow.showAsDropDown(baseBinding.viewTitleMain.tvMenuTitle,0,0, Gravity.RIGHT);
        }
    }
    @Override
    protected boolean onBackIntercept() {
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
            return true;
        }
        finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.me_tasks);
    }

    @Override
    public void onBindItem(Object bean, ViewDataBinding binding, final int position) {
        final TaskListBean.TaskItemBean taskInfoBean= (TaskListBean.TaskItemBean) bean;
        ItemTaskBinding itemTaskBinding= (ItemTaskBinding) binding;
        itemTaskBinding.setBean(taskInfoBean);
        itemTaskBinding.llItemTask.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                TaskInfoActivity.intoPage(MyTaskActivity.this,taskInfoBean.getTaskId());
            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id){
            case R.id.rb_sended_mytask:
                currentPosition=0;
                break;
            case R.id.rb_got_mytask:
                currentPosition=1;
                break;
            case R.id.rb_pay_mytask:
                currentPosition=2;
                break;
            case R.id.rb_complete_mytask:
                currentPosition=3;
                break;
        }
        binding.vpMytask.setCurrentItem(currentPosition);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition=position;
        RadioButton childAt = (RadioButton) binding.rgMytask.getChildAt(position+position);
        childAt.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        binding.vpMytask.removeAllViews();
        SendPackageToServer.getInstance().cancal(UDPConfig.ACTION_MY_TASK_LIST);
        super.onDestroy();
    }
}
