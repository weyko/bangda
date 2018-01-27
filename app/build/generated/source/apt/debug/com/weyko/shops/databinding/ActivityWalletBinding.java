package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityWalletBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_banlance_wallet, 1);
        sViewsWithIds.put(R.id.tv_integral_wallet, 2);
        sViewsWithIds.put(R.id.tv_withdraw, 3);
        sViewsWithIds.put(R.id.tv_toast_wallet, 4);
    }
    // views
    private final android.widget.FrameLayout mboundView0;
    public final android.widget.TextView tvBanlanceWallet;
    public final android.widget.TextView tvIntegralWallet;
    public final android.widget.TextView tvToastWallet;
    public final android.widget.TextView tvWithdraw;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityWalletBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvBanlanceWallet = (android.widget.TextView) bindings[1];
        this.tvIntegralWallet = (android.widget.TextView) bindings[2];
        this.tvToastWallet = (android.widget.TextView) bindings[4];
        this.tvWithdraw = (android.widget.TextView) bindings[3];
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

    public static ActivityWalletBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityWalletBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityWalletBinding>inflate(inflater, com.weyko.shops.R.layout.activity_wallet, root, attachToRoot, bindingComponent);
    }
    public static ActivityWalletBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityWalletBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_wallet, null, false), bindingComponent);
    }
    public static ActivityWalletBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityWalletBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_wallet_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityWalletBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}