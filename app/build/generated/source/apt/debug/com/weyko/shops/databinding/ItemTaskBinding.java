package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ItemTaskBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    public final android.widget.LinearLayout llItemTask;
    private final android.widget.TextView mboundView1;
    private final android.widget.TextView mboundView2;
    private final android.widget.TextView mboundView3;
    private final android.widget.TextView mboundView4;
    // variables
    private com.weyko.shops.task.get.TaskListBean.TaskItemBean mBean;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemTaskBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.llItemTask = (android.widget.LinearLayout) bindings[0];
        this.llItemTask.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
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
                setBean((com.weyko.shops.task.get.TaskListBean.TaskItemBean) variable);
                return true;
        }
        return false;
    }

    public void setBean(com.weyko.shops.task.get.TaskListBean.TaskItemBean Bean) {
        this.mBean = Bean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bean);
        super.requestRebind();
    }
    public com.weyko.shops.task.get.TaskListBean.TaskItemBean getBean() {
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
        java.lang.String beanEndPosition = null;
        java.lang.String stringValueOfBeanMoneyReward = null;
        java.lang.String beanTime = null;
        com.weyko.shops.task.get.TaskListBean.TaskItemBean bean = mBean;
        double beanMoneyReward = 0.0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.taskName
                    beanTaskName = bean.getTaskName();
                    // read bean.endPosition
                    beanEndPosition = bean.getEndPosition();
                    // read bean.time
                    beanTime = bean.getTime();
                    // read bean.moneyReward
                    beanMoneyReward = bean.getMoneyReward();
                }


                // read String.valueOf(bean.moneyReward)
                stringValueOfBeanMoneyReward = java.lang.String.valueOf(beanMoneyReward);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, beanTime);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, beanTaskName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, stringValueOfBeanMoneyReward);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, beanEndPosition);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemTaskBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTaskBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemTaskBinding>inflate(inflater, com.weyko.shops.R.layout.item_task, root, attachToRoot, bindingComponent);
    }
    public static ItemTaskBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTaskBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.item_task, null, false), bindingComponent);
    }
    public static ItemTaskBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTaskBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_task_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemTaskBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): bean
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}