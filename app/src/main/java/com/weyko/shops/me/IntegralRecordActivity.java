package com.weyko.shops.me;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.adapter.BaseListAdapter;
import com.weyko.shops.adapter.BaseListViewHolder;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.bean.IntegralListBean;
import com.weyko.shops.bean.RecodeListBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ItemIntegralBinding;
import com.weyko.shops.databinding.LayoutListBinding;
import com.weyko.shops.manager.ListManager;
import com.weyko.shops.manager.ShowLoadManager;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ThemeUtil;
/**
 * Description:积分记录页面
 * Created  by: weyko on 2017/7/27.
 */

public class IntegralRecordActivity extends BaseActivity<LayoutListBinding> implements ListManager.OnListDataLoader, BaseListViewHolder.OnBindItemListener {
    private ListManager listManager;
    private ShowLoadManager showLoadManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.layout_list;
    }

    @Override
    protected void initData() {
        showBack();
        String title=getIntent().getStringExtra("title");
        showTitle(title);
        showLoadManager=new ShowLoadManager(this,binding.srlFramentGet);
        setData();
    }

    private void setData() {
        listManager=new ListManager(binding,new BaseListAdapter<IntegralListBean.IntegralItemBean,ItemIntegralBinding>(R.layout.item_integral,this),this);
        showLoadManager.showLoading();
        listManager.loadList();
        showLoadManager.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoadManager.showLoading();
                listManager.loadList();
            }
        });
    }

    @Override
    protected boolean onBackIntercept() {
        SendPackageToServer.getInstance().cancal(UDPConfig.ACTION_INTEGRAL_INFO);
        finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return null;
    }

    @Override
    public void loadList(final int startIndex) {
        RecodeListBean.RecodeSubmit submit=new RecodeListBean.RecodeSubmit();
        submit.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        submit.setInstruct(UDPConfig.ACTION_INTEGRAL_INFO);
        submit.setPageIndex(startIndex);
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(submit), IntegralListBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    if(result instanceof IntegralListBean) {
                        IntegralListBean bean = (IntegralListBean) result;
                        listManager.updateAdapter(bean.getData());
                        showLoadManager.showContentView();
                    }else{
                        showLoadManager.showEmpty(getString(R.string.record_empty_integral));
                    }
                }else{
                    showLoadManager.showError();
                }
                listManager.updateRefreshing(false);
            }
        });
    }
    @Override
    public void onBindItem(Object bean, ViewDataBinding binding, int position) {
        ItemIntegralBinding itemBinding= (ItemIntegralBinding) binding;
        itemBinding.setBean((IntegralListBean.IntegralItemBean) bean);
        itemBinding.llItemIntegral.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {

            }
        });
    }
}
