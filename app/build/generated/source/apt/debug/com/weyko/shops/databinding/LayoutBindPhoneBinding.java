package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class LayoutBindPhoneBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_step_first, 1);
        sViewsWithIds.put(R.id.cet_account_bindphone, 2);
        sViewsWithIds.put(R.id.cet_code_bindphone, 3);
        sViewsWithIds.put(R.id.tv_code_bindphone, 4);
        sViewsWithIds.put(R.id.tv_next_bindphone, 5);
        sViewsWithIds.put(R.id.ll_step_second, 6);
        sViewsWithIds.put(R.id.cet_pwd_bindphone, 7);
        sViewsWithIds.put(R.id.cet_pwd_again_bindphone, 8);
        sViewsWithIds.put(R.id.tv_submit_bindphone, 9);
    }
    // views
    public final com.weyko.shops.view.DrawableClickableEditText cetAccountBindphone;
    public final com.weyko.shops.view.DrawableClickableEditText cetCodeBindphone;
    public final com.weyko.shops.view.DrawableClickableEditText cetPwdAgainBindphone;
    public final com.weyko.shops.view.DrawableClickableEditText cetPwdBindphone;
    public final android.widget.LinearLayout llStepFirst;
    public final android.widget.LinearLayout llStepSecond;
    private final android.widget.FrameLayout mboundView0;
    public final android.widget.TextView tvCodeBindphone;
    public final com.weyko.shops.view.CircularProgress.CircularProgressButton tvNextBindphone;
    public final android.widget.TextView tvSubmitBindphone;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutBindPhoneBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.cetAccountBindphone = (com.weyko.shops.view.DrawableClickableEditText) bindings[2];
        this.cetCodeBindphone = (com.weyko.shops.view.DrawableClickableEditText) bindings[3];
        this.cetPwdAgainBindphone = (com.weyko.shops.view.DrawableClickableEditText) bindings[8];
        this.cetPwdBindphone = (com.weyko.shops.view.DrawableClickableEditText) bindings[7];
        this.llStepFirst = (android.widget.LinearLayout) bindings[1];
        this.llStepSecond = (android.widget.LinearLayout) bindings[6];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCodeBindphone = (android.widget.TextView) bindings[4];
        this.tvNextBindphone = (com.weyko.shops.view.CircularProgress.CircularProgressButton) bindings[5];
        this.tvSubmitBindphone = (android.widget.TextView) bindings[9];
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

    public static LayoutBindPhoneBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutBindPhoneBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<LayoutBindPhoneBinding>inflate(inflater, com.weyko.shops.R.layout.layout_bind_phone, root, attachToRoot, bindingComponent);
    }
    public static LayoutBindPhoneBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutBindPhoneBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.layout_bind_phone, null, false), bindingComponent);
    }
    public static LayoutBindPhoneBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutBindPhoneBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/layout_bind_phone_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new LayoutBindPhoneBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}