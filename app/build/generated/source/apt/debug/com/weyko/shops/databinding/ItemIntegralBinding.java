package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ItemIntegralBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_item_integral, 5);
    }
    // views
    public final android.widget.RelativeLayout llItemIntegral;
    private final android.widget.LinearLayout mboundView0;
    private final android.widget.TextView mboundView3;
    private final android.widget.TextView mboundView4;
    public final android.widget.TextView tvDate;
    public final android.widget.TextView tvTitleItemIntegral;
    // variables
    private com.weyko.shops.bean.IntegralListBean.IntegralItemBean mBean;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemIntegralBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.llItemIntegral = (android.widget.RelativeLayout) bindings[5];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.tvDate = (android.widget.TextView) bindings[2];
        this.tvDate.setTag(null);
        this.tvTitleItemIntegral = (android.widget.TextView) bindings[1];
        this.tvTitleItemIntegral.setTag(null);
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
                setBean((com.weyko.shops.bean.IntegralListBean.IntegralItemBean) variable);
                return true;
        }
        return false;
    }

    public void setBean(com.weyko.shops.bean.IntegralListBean.IntegralItemBean Bean) {
        this.mBean = Bean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bean);
        super.requestRebind();
    }
    public com.weyko.shops.bean.IntegralListBean.IntegralItemBean getBean() {
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
        double beanIntegral = 0.0;
        java.lang.String stringValueOfBeanIntegral = null;
        java.lang.String beanTime = null;
        java.lang.String beanDate = null;
        com.weyko.shops.bean.IntegralListBean.IntegralItemBean bean = mBean;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.taskName
                    beanTaskName = bean.getTaskName();
                    // read bean.integral
                    beanIntegral = bean.getIntegral();
                    // read bean.time
                    beanTime = bean.getTime();
                    // read bean.date
                    beanDate = bean.getDate();
                }


                // read String.valueOf(bean.integral)
                stringValueOfBeanIntegral = java.lang.String.valueOf(beanIntegral);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, beanTime);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, stringValueOfBeanIntegral);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, beanDate);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitleItemIntegral, beanTaskName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemIntegralBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemIntegralBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemIntegralBinding>inflate(inflater, com.weyko.shops.R.layout.item_integral, root, attachToRoot, bindingComponent);
    }
    public static ItemIntegralBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemIntegralBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.item_integral, null, false), bindingComponent);
    }
    public static ItemIntegralBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemIntegralBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_integral_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemIntegralBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): bean
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}