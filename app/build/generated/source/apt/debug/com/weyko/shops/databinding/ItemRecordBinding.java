package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ItemRecordBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_item_record, 5);
    }
    // views
    public final android.widget.RelativeLayout llItemRecord;
    private final android.widget.LinearLayout mboundView0;
    private final android.widget.TextView mboundView3;
    private final android.widget.TextView mboundView4;
    public final android.widget.TextView tvDate;
    public final android.widget.TextView tvTitleItemRecord;
    // variables
    private com.weyko.shops.bean.RecodeListBean.RecodeItemBean mBean;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRecordBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.llItemRecord = (android.widget.RelativeLayout) bindings[5];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.tvDate = (android.widget.TextView) bindings[2];
        this.tvDate.setTag(null);
        this.tvTitleItemRecord = (android.widget.TextView) bindings[1];
        this.tvTitleItemRecord.setTag(null);
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
                setBean((com.weyko.shops.bean.RecodeListBean.RecodeItemBean) variable);
                return true;
        }
        return false;
    }

    public void setBean(com.weyko.shops.bean.RecodeListBean.RecodeItemBean Bean) {
        this.mBean = Bean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bean);
        super.requestRebind();
    }
    public com.weyko.shops.bean.RecodeListBean.RecodeItemBean getBean() {
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
        boolean beanTypeInt0 = false;
        java.lang.String beanTypeInt0JavaLangStringJavaLangString = null;
        java.lang.String beanTypeInt0JavaLangStringJavaLangStringBeanMoney = null;
        int beanType = 0;
        double beanMoney = 0.0;
        int beanTypeInt0MboundView4AndroidColorColorPrimaryMboundView4AndroidColorColorGreen = 0;
        java.lang.String beanTime = null;
        java.lang.String BeanTypeInt0JavaLangStringJavaLangString1 = null;
        java.lang.String beanDate = null;
        com.weyko.shops.bean.RecodeListBean.RecodeItemBean bean = mBean;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.type
                    beanType = bean.getType();
                    // read bean.money
                    beanMoney = bean.getMoney();
                    // read bean.time
                    beanTime = bean.getTime();
                    // read bean.date
                    beanDate = bean.getDate();
                }


                // read bean.type == 0
                beanTypeInt0 = (beanType) == (0);
            if((dirtyFlags & 0x3L) != 0) {
                if(beanTypeInt0) {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                        dirtyFlags |= 0x80L;
                }
                else {
                        dirtyFlags |= 0x4L;
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                }
            }


                // read bean.type == 0 ? "提现" : "收入"
                beanTypeInt0JavaLangStringJavaLangString = ((beanTypeInt0) ? ("提现") : ("收入"));
                // read bean.type == 0 ? @android:color/colorPrimary : @android:color/color_green
                beanTypeInt0MboundView4AndroidColorColorPrimaryMboundView4AndroidColorColorGreen = ((beanTypeInt0) ? (getColorFromResource(mboundView4, R.color.colorPrimary)) : (getColorFromResource(mboundView4, R.color.color_green)));
                // read bean.type == 0 ? "-" : "+"
                BeanTypeInt0JavaLangStringJavaLangString1 = ((beanTypeInt0) ? ("-") : ("+"));


                // read (bean.type == 0 ? "-" : "+") + (bean.money)
                beanTypeInt0JavaLangStringJavaLangStringBeanMoney = (BeanTypeInt0JavaLangStringJavaLangString1) + (beanMoney);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, beanTime);
            this.mboundView4.setTextColor(beanTypeInt0MboundView4AndroidColorColorPrimaryMboundView4AndroidColorColorGreen);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, beanTypeInt0JavaLangStringJavaLangStringBeanMoney);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, beanDate);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitleItemRecord, beanTypeInt0JavaLangStringJavaLangString);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemRecordBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemRecordBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemRecordBinding>inflate(inflater, com.weyko.shops.R.layout.item_record, root, attachToRoot, bindingComponent);
    }
    public static ItemRecordBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemRecordBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.item_record, null, false), bindingComponent);
    }
    public static ItemRecordBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemRecordBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_record_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemRecordBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): bean
        flag 1 (0x2L): null
        flag 2 (0x3L): bean.type == 0 ? "提现" : "收入"
        flag 3 (0x4L): bean.type == 0 ? "提现" : "收入"
        flag 4 (0x5L): bean.type == 0 ? @android:color/colorPrimary : @android:color/color_green
        flag 5 (0x6L): bean.type == 0 ? @android:color/colorPrimary : @android:color/color_green
        flag 6 (0x7L): bean.type == 0 ? "-" : "+"
        flag 7 (0x8L): bean.type == 0 ? "-" : "+"
    flag mapping end*/
    //end
}