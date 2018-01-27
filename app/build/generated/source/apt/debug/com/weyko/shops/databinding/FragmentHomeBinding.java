package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class FragmentHomeBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(11);
        sIncludes.setIncludes(0, 
            new String[] {"layout_address"},
            new int[] {5},
            new int[] {R.layout.layout_address});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.sv_fragment_home, 6);
        sViewsWithIds.put(R.id.ll_main_home_fragment, 7);
        sViewsWithIds.put(R.id.cb_area_main, 8);
        sViewsWithIds.put(R.id.et_money_main, 9);
        sViewsWithIds.put(R.id.tv_send_main, 10);
    }
    // views
    public final android.widget.CheckBox cbAreaMain;
    public final com.weyko.shops.view.DrawableClickableEditText etMarkMain;
    public final com.weyko.shops.view.DrawableClickableEditText etMoneyMain;
    public final com.weyko.shops.view.DrawableClickableEditText etNeedMain;
    public final android.widget.LinearLayout llMainHomeFragment;
    private final android.widget.FrameLayout mboundView0;
    public final android.widget.TextView startPositionMain;
    public final android.widget.ScrollView svFragmentHome;
    public final com.weyko.shops.view.DrawableClickableEditText tvDestinationMain;
    public final com.weyko.shops.view.CircularProgress.CircularProgressButton tvSendMain;
    public final com.weyko.shops.databinding.LayoutAddressBinding viewAddress;
    // variables
    private com.weyko.shops.task.send.SendData mModel;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private android.databinding.InverseBindingListener etMarkMainandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.taskDescribe
            //         is model.setTaskDescribe((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(etMarkMain);
            // localize variables for thread safety
            // model
            com.weyko.shops.task.send.SendData model = mModel;
            // model != null
            boolean modelJavaLangObjectNull = false;
            // model.taskDescribe
            java.lang.String modelTaskDescribe = null;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setTaskDescribe(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private android.databinding.InverseBindingListener etNeedMainandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.taskName
            //         is model.setTaskName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(etNeedMain);
            // localize variables for thread safety
            // model
            com.weyko.shops.task.send.SendData model = mModel;
            // model.taskName
            java.lang.String modelTaskName = null;
            // model != null
            boolean modelJavaLangObjectNull = false;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setTaskName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private android.databinding.InverseBindingListener startPositionMainandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.startPosition
            //         is model.setStartPosition((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(startPositionMain);
            // localize variables for thread safety
            // model
            com.weyko.shops.task.send.SendData model = mModel;
            // model != null
            boolean modelJavaLangObjectNull = false;
            // model.startPosition
            java.lang.String modelStartPosition = null;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setStartPosition(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private android.databinding.InverseBindingListener tvDestinationMainandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.destination
            //         is model.setDestination((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(tvDestinationMain);
            // localize variables for thread safety
            // model
            com.weyko.shops.task.send.SendData model = mModel;
            // model != null
            boolean modelJavaLangObjectNull = false;
            // model.destination
            java.lang.String modelDestination = null;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setDestination(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public FragmentHomeBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.cbAreaMain = (android.widget.CheckBox) bindings[8];
        this.etMarkMain = (com.weyko.shops.view.DrawableClickableEditText) bindings[4];
        this.etMarkMain.setTag(null);
        this.etMoneyMain = (com.weyko.shops.view.DrawableClickableEditText) bindings[9];
        this.etNeedMain = (com.weyko.shops.view.DrawableClickableEditText) bindings[1];
        this.etNeedMain.setTag(null);
        this.llMainHomeFragment = (android.widget.LinearLayout) bindings[7];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.startPositionMain = (android.widget.TextView) bindings[3];
        this.startPositionMain.setTag(null);
        this.svFragmentHome = (android.widget.ScrollView) bindings[6];
        this.tvDestinationMain = (com.weyko.shops.view.DrawableClickableEditText) bindings[2];
        this.tvDestinationMain.setTag(null);
        this.tvSendMain = (com.weyko.shops.view.CircularProgress.CircularProgressButton) bindings[10];
        this.viewAddress = (com.weyko.shops.databinding.LayoutAddressBinding) bindings[5];
        setContainedBinding(this.viewAddress);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        viewAddress.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (viewAddress.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.model :
                setModel((com.weyko.shops.task.send.SendData) variable);
                return true;
        }
        return false;
    }

    public void setModel(com.weyko.shops.task.send.SendData Model) {
        this.mModel = Model;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public com.weyko.shops.task.send.SendData getModel() {
        return mModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewAddress((com.weyko.shops.databinding.LayoutAddressBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewAddress(com.weyko.shops.databinding.LayoutAddressBinding ViewAddress, int fieldId) {
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
        java.lang.String modelDestination = null;
        java.lang.String modelStartPosition = null;
        com.weyko.shops.task.send.SendData model = mModel;
        java.lang.String modelTaskName = null;
        java.lang.String modelTaskDescribe = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (model != null) {
                    // read model.destination
                    modelDestination = model.getDestination();
                    // read model.startPosition
                    modelStartPosition = model.getStartPosition();
                    // read model.taskName
                    modelTaskName = model.getTaskName();
                    // read model.taskDescribe
                    modelTaskDescribe = model.getTaskDescribe();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.etMarkMain, modelTaskDescribe);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.etNeedMain, modelTaskName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.startPositionMain, modelStartPosition);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDestinationMain, modelDestination);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etMarkMain, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etMarkMainandroidTextAttrChanged);
            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etNeedMain, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etNeedMainandroidTextAttrChanged);
            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.startPositionMain, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, startPositionMainandroidTextAttrChanged);
            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tvDestinationMain, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tvDestinationMainandroidTextAttrChanged);
        }
        executeBindingsOn(viewAddress);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static FragmentHomeBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentHomeBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentHomeBinding>inflate(inflater, com.weyko.shops.R.layout.fragment_home, root, attachToRoot, bindingComponent);
    }
    public static FragmentHomeBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentHomeBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.fragment_home, null, false), bindingComponent);
    }
    public static FragmentHomeBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentHomeBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_home_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentHomeBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewAddress
        flag 1 (0x2L): model
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}