package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class FragmentBaseBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"layout_page_load"},
            new int[] {1},
            new int[] {R.layout.layout_page_load});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_content_part, 2);
    }
    // views
    public final android.widget.LinearLayout llRoot;
    public final android.widget.RelativeLayout rlContentPart;
    public final com.weyko.shops.databinding.LayoutPageLoadBinding viewContainFragment;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentBaseBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.llRoot = (android.widget.LinearLayout) bindings[0];
        this.llRoot.setTag(null);
        this.rlContentPart = (android.widget.RelativeLayout) bindings[2];
        this.viewContainFragment = (com.weyko.shops.databinding.LayoutPageLoadBinding) bindings[1];
        setContainedBinding(this.viewContainFragment);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        viewContainFragment.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (viewContainFragment.hasPendingBindings()) {
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
                return onChangeViewContainFragment((com.weyko.shops.databinding.LayoutPageLoadBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewContainFragment(com.weyko.shops.databinding.LayoutPageLoadBinding ViewContainFragment, int fieldId) {
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
        executeBindingsOn(viewContainFragment);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static FragmentBaseBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentBaseBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentBaseBinding>inflate(inflater, com.weyko.shops.R.layout.fragment_base, root, attachToRoot, bindingComponent);
    }
    public static FragmentBaseBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentBaseBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.fragment_base, null, false), bindingComponent);
    }
    public static FragmentBaseBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentBaseBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_base_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentBaseBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewContainFragment
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}