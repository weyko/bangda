package com.weyko.shops.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.adapter.BaseListAdapter;
import com.weyko.shops.adapter.BaseListViewHolder;
import com.weyko.shops.base.BaseBean;
import com.weyko.shops.base.BaseFragment;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ItemTaskBinding;
import com.weyko.shops.databinding.LayoutListBinding;
import com.weyko.shops.manager.ListManager;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.task.get.TaskInfoBean;
import com.weyko.shops.task.get.TaskListBean;
import com.weyko.shops.task.send.TaskInfoActivity;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

/**
 * Description:
 * Created  by: weyko on 2017/6/5.
 */

public class GetTaskFragment extends BaseFragment<LayoutListBinding> implements ListManager.OnListDataLoader, BaseListViewHolder.OnBindItemListener {
    private boolean mIsPrepared=false;
        private boolean mIsFirst = true;
    private ListManager listManager;
    private boolean isEmpty=false;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.srlFramentGet.setColorSchemeColors(CommonUtils.getColor(R.color.colorPrimary));
        showLoading();
        listManager=new ListManager(binding,new BaseListAdapter<TaskInfoBean,ItemTaskBinding>(R.layout.item_task,this),this);
        // 准备就绪
        mIsPrepared = true;
    }

    @Override
    public void loadData(final int startIndex) {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        listManager.loadList();
    }
    @Override
    public int setContent() {
        return R.layout.layout_list;
    }
    @Override
    protected void onRefresh() {
        listManager.updateRefreshing(true);
        listManager.loadList();
    }
    @Override
    public void onBindItem(Object bean, ViewDataBinding binding, int position) {
        final TaskListBean.TaskItemBean taskInfoBean= (TaskListBean.TaskItemBean) bean;
        ItemTaskBinding itemTaskBinding= (ItemTaskBinding) binding;
        itemTaskBinding.setBean(taskInfoBean);
        itemTaskBinding.llItemTask.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                TaskInfoActivity.intoPage(getActivity(),taskInfoBean.getTaskId());
            }
        });
    }

    @Override
    public void loadList(int startIndex) {
        TaskListBean.TaskListSubmit submit=new TaskListBean.TaskListSubmit();
        submit.setInstruct(UDPConfig.ACTION_TASK_LIST);
        submit.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        submit.setLatitude(SaveDataUtil.getInstance().getLatitude());
        submit.setLgitude(SaveDataUtil.getInstance().getLgitude());
        submit.setPageIndex(startIndex);
        SendPackageToServer.getInstance().sendData(getActivity(), ConvertTool.toJsonStr(submit), TaskListBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                isEmpty=false;
                if(result==null){
                    showError();
                }else{
                    if(result instanceof TaskInfoBean) {
                        TaskListBean listBean = (TaskListBean) result;
                        listManager.updateAdapter(listBean.getData());
                         showContentView();
                    }else  if(result instanceof BaseBean){
                        isEmpty=true;
                        showEmpty("");
                    }
                    listManager.updateRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden&&isEmpty){
            ShowUtil.d("GetTaskFragment--->onHiddenChanged");
           loadList(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listManager.updateRefreshing(false);
    }
}
