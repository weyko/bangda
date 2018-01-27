package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class DialogPaymentOptionBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_close, 1);
        sViewsWithIds.put(R.id.rg_payment_option, 2);
        sViewsWithIds.put(R.id.rb_alipay_payment, 3);
        sViewsWithIds.put(R.id.rb_wxpay_payment, 4);
        sViewsWithIds.put(R.id.confirm_btn, 5);
    }
    // views
    public final android.widget.TextView confirmBtn;
    public final android.widget.ImageView ivClose;
    public final android.widget.LinearLayout layout;
    public final android.widget.RadioButton rbAlipayPayment;
    public final android.widget.RadioButton rbWxpayPayment;
    public final android.widget.RadioGroup rgPaymentOption;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DialogPaymentOptionBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.confirmBtn = (android.widget.TextView) bindings[5];
        this.ivClose = (android.widget.ImageView) bindings[1];
        this.layout = (android.widget.LinearLayout) bindings[0];
        this.layout.setTag(null);
        this.rbAlipayPayment = (android.widget.RadioButton) bindings[3];
        this.rbWxpayPayment = (android.widget.RadioButton) bindings[4];
        this.rgPaymentOption = (android.widget.RadioGroup) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
        }
        return false;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static DialogPaymentOptionBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static DialogPaymentOptionBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogPaymentOptionBinding>inflate(inflater, com.weyko.shops.R.layout.dialog_payment_option, root, attachToRoot, bindingComponent);
    }
    public static DialogPaymentOptionBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static DialogPaymentOptionBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.dialog_payment_option, null, false), bindingComponent);
    }
    public static DialogPaymentOptionBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static DialogPaymentOptionBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_payment_option_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogPaymentOptionBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}