package com.weyko.shops.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.weyko.shops.R;
import com.weyko.shops.databinding.FragmentBaseBinding;
import com.weyko.shops.manager.ShowLoadManager;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ShowUtil;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 是没有title的Fragment
 */
public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment {

    // 布局view
    protected SV binding;
    // fragment是否显示了
    protected boolean mIsVisible = false;
    // 内容布局
    protected LinearLayout mContainer;
    private CompositeSubscription mCompositeSubscription;
    private ShowLoadManager showLoadManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View ll = inflater.inflate(R.layout.fragment_base, null);
        FragmentBaseBinding baseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, true);
        binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity= Gravity.CENTER;
        binding.getRoot().setLayoutParams(params);
//        mContainer = (LinearLayout) ll.findViewById(R.id.container);
        baseBinding.viewContainFragment.container.addView(binding.getRoot());
        return baseBinding.getRoot();
    }

    /**
     * 隐藏软键盘
     * @param viewMain
     */
    public void hideSoftWindow(View viewMain){
        if(viewMain==null)return;
        ShowUtil.hideSoftWindow(viewMain.getContext(),viewMain);
    }
    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData(int startIndex) {
    }

    protected void onVisible() {
        loadData(0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoadManager=new ShowLoadManager(getView(),binding.getRoot());
        // 点击加载失败布局
        showLoadManager.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        binding.getRoot().setVisibility(View.GONE);
    }

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 布局
     */
    public abstract int setContent();

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
         showLoadManager.showLoading();
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        showLoadManager.showContentView();
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        showLoadManager.showError();
    }
    /**
     * 加载失败点击重新加载的状态
     */
    protected void showEmpty(String emptyHint) {
        showLoadManager.showEmpty(emptyHint);
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }
    public void startActivity(Class clzz){
        Intent intent=new Intent(getActivity(),clzz);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right,0);
    }
    public void startActivity(Class clzz,Bundle bundle){
        Intent intent=new Intent(getActivity(),clzz);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right,0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
