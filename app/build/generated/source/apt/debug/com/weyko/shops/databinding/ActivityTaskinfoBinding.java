package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityTaskinfoBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.sview_taskinfo, 8);
        sViewsWithIds.put(R.id.ll_task_info, 9);
        sViewsWithIds.put(R.id.tsv_taskinfo, 10);
        sViewsWithIds.put(R.id.ll_money_taskinfo, 11);
        sViewsWithIds.put(R.id.et_goods_money_taskinfo, 12);
        sViewsWithIds.put(R.id.textView, 13);
        sViewsWithIds.put(R.id.tv_get_task, 14);
    }
    // views
    public final android.widget.EditText etGoodsMoneyTaskinfo;
    public final android.widget.LinearLayout llMoneyTaskinfo;
    public final android.widget.LinearLayout llTaskInfo;
    private final android.widget.FrameLayout mboundView0;
    private final android.widget.TextView mboundView1;
    private final android.widget.TextView mboundView2;
    private final android.widget.TextView mboundView3;
    private final android.widget.LinearLayout mboundView4;
    private final android.widget.TextView mboundView5;
    private final android.widget.TextView mboundView6;
    private final android.widget.TextView mboundView7;
    public final com.weyko.shops.view.OnScrollChangerScrollView sviewTaskinfo;
    public final android.widget.TextView textView;
    public final com.weyko.shops.view.TaskStatueView tsvTaskinfo;
    public final android.widget.TextView tvGetTask;
    // variables
    private com.weyko.shops.task.get.TaskInfoBean.TaskInfoData mBean;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTaskinfoBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.etGoodsMoneyTaskinfo = (android.widget.EditText) bindings[12];
        this.llMoneyTaskinfo = (android.widget.LinearLayout) bindings[11];
        this.llTaskInfo = (android.widget.LinearLayout) bindings[9];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.LinearLayout) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.sviewTaskinfo = (com.weyko.shops.view.OnScrollChangerScrollView) bindings[8];
        this.textView = (android.widget.TextView) bindings[13];
        this.tsvTaskinfo = (com.weyko.shops.view.TaskStatueView) bindings[10];
        this.tvGetTask = (android.widget.TextView) bindings[14];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.bean :
                setBean((com.weyko.shops.task.get.TaskInfoBean.TaskInfoData) variable);
                return true;
        }
        return false;
    }

    public void setBean(com.weyko.shops.task.get.TaskInfoBean.TaskInfoData Bean) {
        this.mBean = Bean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bean);
        super.requestRebind();
    }
    public com.weyko.shops.task.get.TaskInfoBean.TaskInfoData getBean() {
        return mBean;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String beanTaskName = null;
        java.lang.String beanStartPosition = null;
        java.lang.String beanTaskDescribe = null;
        java.lang.String beanDestination = null;
        java.lang.String beanSendTime = null;
        int beanIsCrosstownViewVISIBLEViewGONE = 0;
        java.lang.String stringValueOfBeanMoneyReward = null;
        boolean beanIsCrosstown = false;
        com.weyko.shops.task.get.TaskInfoBean.TaskInfoData bean = mBean;
        double beanMoneyReward = 0.0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.taskName
                    beanTaskName = bean.getTaskName();
                    // read bean.startPosition
                    beanStartPosition = bean.getStartPosition();
                    // read bean.taskDescribe
                    beanTaskDescribe = bean.getTaskDescribe();
                    // read bean.destination
                    beanDestination = bean.getDestination();
                    // read bean.sendTime
                    beanSendTime = bean.getSendTime();
                    // read bean.isCrosstown
                    beanIsCrosstown = bean.isCrosstown();
                    // read bean.moneyReward
                    beanMoneyReward = bean.getMoneyReward();
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(beanIsCrosstown) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read bean.isCrosstown ? View.VISIBLE : View.GONE
                beanIsCrosstownViewVISIBLEViewGONE = ((beanIsCrosstown) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read String.valueOf(bean.moneyReward)
                stringValueOfBeanMoneyReward = java.lang.String.valueOf(beanMoneyReward);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, beanTaskName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, beanTaskDescribe);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, stringValueOfBeanMoneyReward);
            this.mboundView4.setVisibility(beanIsCrosstownViewVISIBLEViewGONE);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, beanStartPosition);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, beanDestination);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, beanSendTime);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityTaskinfoBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityTaskinfoBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityTaskinfoBinding>inflate(inflater, com.weyko.shops.R.layout.activity_taskinfo, root, attachToRoot, bindingComponent);
    }
    public static ActivityTaskinfoBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityTaskinfoBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_taskinfo, null, false), bindingComponent);
    }
    public static ActivityTaskinfoBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityTaskinfoBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_taskinfo_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityTaskinfoBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): bean
        flag 1 (0x2L): null
        flag 2 (0x3L): bean.isCrosstown ? View.VISIBLE : View.GONE
        flag 3 (0x4L): bean.isCrosstown ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}