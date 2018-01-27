package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class LayoutDialogBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_root_dialog, 1);
        sViewsWithIds.put(R.id.tv_title_dialog, 2);
        sViewsWithIds.put(R.id.tv_msg_dialog, 3);
        sViewsWithIds.put(R.id.tv_hint_dialog, 4);
        sViewsWithIds.put(R.id.tv_cancal_dialog, 5);
        sViewsWithIds.put(R.id.tv_sure_dialog, 6);
    }
    // views
    public final android.widget.LinearLayout llRootDialog;
    private final android.widget.LinearLayout mboundView0;
    public final android.widget.TextView tvCancalDialog;
    public final android.widget.TextView tvHintDialog;
    public final android.widget.TextView tvMsgDialog;
    public final android.widget.TextView tvSureDialog;
    public final android.widget.TextView tvTitleDialog;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutDialogBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.llRootDialog = (android.widget.LinearLayout) bindings[1];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCancalDialog = (android.widget.TextView) bindings[5];
        this.tvHintDialog = (android.widget.TextView) bindings[4];
        this.tvMsgDialog = (android.widget.TextView) bindings[3];
        this.tvSureDialog = (android.widget.TextView) bindings[6];
        this.tvTitleDialog = (android.widget.TextView) bindings[2];
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

    public static LayoutDialogBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutDialogBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<LayoutDialogBinding>inflate(inflater, com.weyko.shops.R.layout.layout_dialog, root, attachToRoot, bindingComponent);
    }
    public static LayoutDialogBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutDialogBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.layout_dialog, null, false), bindingComponent);
    }
    public static LayoutDialogBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutDialogBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/layout_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new LayoutDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}