package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityMytaskBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(9);
        sIncludes.setIncludes(0, 
            new String[] {"layout_list"},
            new int[] {1},
            new int[] {R.layout.layout_list});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rg_mytask, 2);
        sViewsWithIds.put(R.id.rb_sended_mytask, 3);
        sViewsWithIds.put(R.id.rb_got_mytask, 4);
        sViewsWithIds.put(R.id.rb_pay_mytask, 5);
        sViewsWithIds.put(R.id.rb_complete_mytask, 6);
        sViewsWithIds.put(R.id.tv_counts_mytask, 7);
        sViewsWithIds.put(R.id.vp_mytask, 8);
    }
    // views
    private final android.widget.LinearLayout mboundView0;
    public final android.widget.RadioButton rbCompleteMytask;
    public final android.widget.RadioButton rbGotMytask;
    public final android.widget.RadioButton rbPayMytask;
    public final android.widget.RadioButton rbSendedMytask;
    public final android.widget.RadioGroup rgMytask;
    public final android.widget.TextView tvCountsMytask;
    public final com.weyko.shops.databinding.LayoutListBinding viewListMytask;
    public final android.support.v4.view.ViewPager vpMytask;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMytaskBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rbCompleteMytask = (android.widget.RadioButton) bindings[6];
        this.rbGotMytask = (android.widget.RadioButton) bindings[4];
        this.rbPayMytask = (android.widget.RadioButton) bindings[5];
        this.rbSendedMytask = (android.widget.RadioButton) bindings[3];
        this.rgMytask = (android.widget.RadioGroup) bindings[2];
        this.tvCountsMytask = (android.widget.TextView) bindings[7];
        this.viewListMytask = (com.weyko.shops.databinding.LayoutListBinding) bindings[1];
        setContainedBinding(this.viewListMytask);
        this.vpMytask = (android.support.v4.view.ViewPager) bindings[8];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        viewListMytask.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (viewListMytask.hasPendingBindings()) {
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
                return onChangeViewListMytask((com.weyko.shops.databinding.LayoutListBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewListMytask(com.weyko.shops.databinding.LayoutListBinding ViewListMytask, int fieldId) {
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
        executeBindingsOn(viewListMytask);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityMytaskBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMytaskBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMytaskBinding>inflate(inflater, com.weyko.shops.R.layout.activity_mytask, root, attachToRoot, bindingComponent);
    }
    public static ActivityMytaskBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMytaskBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_mytask, null, false), bindingComponent);
    }
    public static ActivityMytaskBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMytaskBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_mytask_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMytaskBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewListMytask
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}