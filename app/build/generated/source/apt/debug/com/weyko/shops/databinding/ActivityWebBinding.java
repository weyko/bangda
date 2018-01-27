package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityWebBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"layout_title"},
            new int[] {1},
            new int[] {R.layout.layout_title});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.webview, 2);
    }
    // views
    public final android.widget.RelativeLayout rl;
    public final com.weyko.shops.databinding.LayoutTitleBinding webTitle;
    public final com.weyko.shops.view.ProgressWebView webview;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityWebBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.rl = (android.widget.RelativeLayout) bindings[0];
        this.rl.setTag(null);
        this.webTitle = (com.weyko.shops.databinding.LayoutTitleBinding) bindings[1];
        setContainedBinding(this.webTitle);
        this.webview = (com.weyko.shops.view.ProgressWebView) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        webTitle.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (webTitle.hasPendingBindings()) {
            return true;
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
            case 0 :
                return onChangeWebTitle((com.weyko.shops.databinding.LayoutTitleBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeWebTitle(com.weyko.shops.databinding.LayoutTitleBinding WebTitle, int fieldId) {
        switch (fieldId) {
            case BR._all: {
                synchronized(this) {
                        mDirtyFlags |= 0x1L;
                }
                return true;
            }
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
        executeBindingsOn(webTitle);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityWebBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityWebBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityWebBinding>inflate(inflater, com.weyko.shops.R.layout.activity_web, root, attachToRoot, bindingComponent);
    }
    public static ActivityWebBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityWebBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_web, null, false), bindingComponent);
    }
    public static ActivityWebBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityWebBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_web_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityWebBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): webTitle
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}