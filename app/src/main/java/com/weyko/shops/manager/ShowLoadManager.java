package com.weyko.shops.manager;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weyko.shops.R;
import com.weyko.shops.util.PerfectClickListener;

/**
 * Description:
 * Created  by: weyko on 2017/8/4.
 */

public class ShowLoadManager {
    // 加载中
    private LinearLayout mLlProgressBar;
    // 加载失败
    private LinearLayout mRefresh;
    // 加载数据为空
    private LinearLayout mEmpty;
    private View containView;
    // 动画
    private AnimationDrawable mAnimationDrawable;
    public ShowLoadManager(FragmentActivity activity,View containView) {
        this.mLlProgressBar = (LinearLayout) activity.findViewById(R.id.ll_progress_bar);
        this.mRefresh = (LinearLayout) activity.findViewById(R.id.ll_error_refresh);
        this.mEmpty =(LinearLayout) activity.findViewById(R.id.ll_empty_refresh);
        this.containView=containView;
        ImageView img= (ImageView) activity.findViewById(R.id.img_progress);
        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }
    public ShowLoadManager(View view,View containView) {
        this.mLlProgressBar = (LinearLayout) view.findViewById(R.id.ll_progress_bar);
        this.mRefresh = (LinearLayout) view.findViewById(R.id.ll_error_refresh);
        this.mEmpty =(LinearLayout) view.findViewById(R.id.ll_empty_refresh);
        this.containView=containView;
        ImageView img= (ImageView) view.findViewById(R.id.img_progress);
        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }
    public void setOnClickListener(PerfectClickListener perfectClickListener){
        if(perfectClickListener!=null){
            mRefresh.setOnClickListener(perfectClickListener);
        }
    }
    /**
     * 显示加载中状态
     */
    public void showLoading() {
        if (mLlProgressBar.getVisibility() != View.VISIBLE) {
            mLlProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (containView.getVisibility() != View.GONE) {
            containView.setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (mEmpty.getVisibility() != View.GONE) {
            mEmpty.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    public void showContentView() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (mEmpty.getVisibility() != View.GONE) {
            mEmpty.setVisibility(View.GONE);
        }
        if (containView.getVisibility() != View.VISIBLE) {
            containView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    public void showError() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (mEmpty.getVisibility() != View.GONE) {
            mEmpty.setVisibility(View.GONE);
        }
        if (containView.getVisibility() != View.GONE) {
            containView.setVisibility(View.GONE);
        }
    }
    /**
     * 加载失败点击重新加载的状态
     */
    public void showEmpty(String emptyHint) {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mEmpty.getVisibility() != View.VISIBLE) {
            mEmpty.setVisibility(View.VISIBLE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (containView.getVisibility() != View.GONE) {
            containView.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(emptyHint)){
            TextView emptyHintView = (TextView) mEmpty.getChildAt(1);
            emptyHintView.setText(emptyHint);
        }
    }
}
