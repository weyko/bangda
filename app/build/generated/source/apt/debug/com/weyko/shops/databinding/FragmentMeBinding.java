package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class FragmentMeBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_header_me, 1);
        sViewsWithIds.put(R.id.tv_name_me, 2);
        sViewsWithIds.put(R.id.tv_level_me, 3);
        sViewsWithIds.put(R.id.tv_tasks_me, 4);
        sViewsWithIds.put(R.id.tv_wallet_me, 5);
        sViewsWithIds.put(R.id.tv_share_me, 6);
        sViewsWithIds.put(R.id.tv_setting_me, 7);
    }
    // views
    public final android.widget.ImageView ivHeaderMe;
    public final android.widget.LinearLayout llMe;
    public final android.widget.TextView tvLevelMe;
    public final android.widget.TextView tvNameMe;
    public final android.widget.TextView tvSettingMe;
    public final android.widget.TextView tvShareMe;
    public final android.widget.TextView tvTasksMe;
    public final android.widget.TextView tvWalletMe;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMeBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.ivHeaderMe = (android.widget.ImageView) bindings[1];
        this.llMe = (android.widget.LinearLayout) bindings[0];
        this.llMe.setTag(null);
        this.tvLevelMe = (android.widget.TextView) bindings[3];
        this.tvNameMe = (android.widget.TextView) bindings[2];
        this.tvSettingMe = (android.widget.TextView) bindings[7];
        this.tvShareMe = (android.widget.TextView) bindings[6];
        this.tvTasksMe = (android.widget.TextView) bindings[4];
        this.tvWalletMe = (android.widget.TextView) bindings[5];
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

    public static FragmentMeBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMeBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMeBinding>inflate(inflater, com.weyko.shops.R.layout.fragment_me, root, attachToRoot, bindingComponent);
    }
    public static FragmentMeBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMeBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.fragment_me, null, false), bindingComponent);
    }
    public static FragmentMeBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMeBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_me_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMeBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}