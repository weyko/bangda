package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class LayoutPageLoadBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll_error_refresh, 1);
        sViewsWithIds.put(R.id.img_err, 2);
        sViewsWithIds.put(R.id.ll_empty_refresh, 3);
        sViewsWithIds.put(R.id.img_empty, 4);
        sViewsWithIds.put(R.id.ll_progress_bar, 5);
        sViewsWithIds.put(R.id.img_progress, 6);
    }
    // views
    public final android.widget.LinearLayout container;
    public final android.widget.ImageView imgEmpty;
    public final android.widget.ImageView imgErr;
    public final android.widget.ImageView imgProgress;
    public final android.widget.LinearLayout llEmptyRefresh;
    public final android.widget.LinearLayout llErrorRefresh;
    public final android.widget.LinearLayout llProgressBar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutPageLoadBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.container = (android.widget.LinearLayout) bindings[0];
        this.container.setTag(null);
        this.imgEmpty = (android.widget.ImageView) bindings[4];
        this.imgErr = (android.widget.ImageView) bindings[2];
        this.imgProgress = (android.widget.ImageView) bindings[6];
        this.llEmptyRefresh = (android.widget.LinearLayout) bindings[3];
        this.llErrorRefresh = (android.widget.LinearLayout) bindings[1];
        this.llProgressBar = (android.widget.LinearLayout) bindings[5];
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

    public static LayoutPageLoadBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutPageLoadBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<LayoutPageLoadBinding>inflate(inflater, com.weyko.shops.R.layout.layout_page_load, root, attachToRoot, bindingComponent);
    }
    public static LayoutPageLoadBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutPageLoadBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.layout_page_load, null, false), bindingComponent);
    }
    public static LayoutPageLoadBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LayoutPageLoadBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/layout_page_load_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new LayoutPageLoadBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}