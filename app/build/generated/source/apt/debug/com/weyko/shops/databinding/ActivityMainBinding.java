package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityMainBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(8);
        sIncludes.setIncludes(0, 
            new String[] {"layout_title"},
            new int[] {1},
            new int[] {R.layout.layout_title});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rg_main, 2);
        sViewsWithIds.put(R.id.rb_home_main, 3);
        sViewsWithIds.put(R.id.rb_get_main, 4);
        sViewsWithIds.put(R.id.rb_me_main, 5);
        sViewsWithIds.put(R.id.line_main, 6);
        sViewsWithIds.put(R.id.vp_main, 7);
    }
    // views
    public final android.view.View lineMain;
    public final android.widget.RelativeLayout llMain;
    public final android.widget.RadioButton rbGetMain;
    public final android.widget.RadioButton rbHomeMain;
    public final android.widget.RadioButton rbMeMain;
    public final android.widget.RadioGroup rgMain;
    public final com.weyko.shops.databinding.LayoutTitleBinding viewTitleMain;
    public final android.support.v4.view.ViewPager vpMain;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.lineMain = (android.view.View) bindings[6];
        this.llMain = (android.widget.RelativeLayout) bindings[0];
        this.llMain.setTag(null);
        this.rbGetMain = (android.widget.RadioButton) bindings[4];
        this.rbHomeMain = (android.widget.RadioButton) bindings[3];
        this.rbMeMain = (android.widget.RadioButton) bindings[5];
        this.rgMain = (android.widget.RadioGroup) bindings[2];
        this.viewTitleMain = (com.weyko.shops.databinding.LayoutTitleBinding) bindings[1];
        setContainedBinding(this.viewTitleMain);
        this.vpMain = (android.support.v4.view.ViewPager) bindings[7];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        viewTitleMain.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (viewTitleMain.hasPendingBindings()) {
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
                return onChangeViewTitleMain((com.weyko.shops.databinding.LayoutTitleBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewTitleMain(com.weyko.shops.databinding.LayoutTitleBinding ViewTitleMain, int fieldId) {
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
        executeBindingsOn(viewTitleMain);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.weyko.shops.R.layout.activity_main, root, attachToRoot, bindingComponent);
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_main, null, false), bindingComponent);
    }
    public static ActivityMainBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_main_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMainBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewTitleMain
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}