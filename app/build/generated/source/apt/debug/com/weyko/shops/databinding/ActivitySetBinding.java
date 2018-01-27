package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivitySetBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_reset_pwd_set, 1);
        sViewsWithIds.put(R.id.tv_checkversion_set, 2);
        sViewsWithIds.put(R.id.tv_version_set, 3);
        sViewsWithIds.put(R.id.tv_reback_set, 4);
        sViewsWithIds.put(R.id.tv_connect_set, 5);
        sViewsWithIds.put(R.id.tv_exit_set, 6);
        sViewsWithIds.put(R.id.tv_service_set, 7);
    }
    // views
    private final android.widget.LinearLayout mboundView0;
    public final android.widget.TextView tvCheckversionSet;
    public final android.widget.TextView tvConnectSet;
    public final android.widget.TextView tvExitSet;
    public final android.widget.TextView tvRebackSet;
    public final android.widget.TextView tvResetPwdSet;
    public final android.widget.TextView tvServiceSet;
    public final android.widget.TextView tvVersionSet;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySetBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCheckversionSet = (android.widget.TextView) bindings[2];
        this.tvConnectSet = (android.widget.TextView) bindings[5];
        this.tvExitSet = (android.widget.TextView) bindings[6];
        this.tvRebackSet = (android.widget.TextView) bindings[4];
        this.tvResetPwdSet = (android.widget.TextView) bindings[1];
        this.tvServiceSet = (android.widget.TextView) bindings[7];
        this.tvVersionSet = (android.widget.TextView) bindings[3];
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

    public static ActivitySetBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivitySetBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivitySetBinding>inflate(inflater, com.weyko.shops.R.layout.activity_set, root, attachToRoot, bindingComponent);
    }
    public static ActivitySetBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivitySetBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_set, null, false), bindingComponent);
    }
    public static ActivitySetBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivitySetBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_set_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivitySetBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}