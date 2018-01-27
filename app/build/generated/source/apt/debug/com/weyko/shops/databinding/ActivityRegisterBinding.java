package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityRegisterBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cet_account_register, 4);
        sViewsWithIds.put(R.id.tv_code_register, 5);
        sViewsWithIds.put(R.id.tv_register_register, 6);
    }
    // views
    public final com.weyko.shops.view.DrawableClickableEditText cetAccountRegister;
    public final com.weyko.shops.view.DrawableClickableEditText cetCodeRegister;
    public final com.weyko.shops.view.DrawableClickableEditText cetPwdRegister;
    public final android.widget.EditText etInviteRegister;
    private final android.widget.LinearLayout mboundView0;
    public final android.widget.TextView tvCodeRegister;
    public final com.weyko.shops.view.CircularProgress.CircularProgressButton tvRegisterRegister;
    // variables
    private com.weyko.shops.login.RegisterBean.RestiterSubmit mModel;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private android.databinding.InverseBindingListener cetCodeRegisterandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.securityCode
            //         is model.setSecurityCode((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(cetCodeRegister);
            // localize variables for thread safety
            // model
            com.weyko.shops.login.RegisterBean.RestiterSubmit model = mModel;
            // model != null
            boolean modelJavaLangObjectNull = false;
            // model.securityCode
            java.lang.String modelSecurityCode = null;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setSecurityCode(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private android.databinding.InverseBindingListener cetPwdRegisterandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.password
            //         is model.setPassword((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(cetPwdRegister);
            // localize variables for thread safety
            // model
            com.weyko.shops.login.RegisterBean.RestiterSubmit model = mModel;
            // model.password
            java.lang.String modelPassword = null;
            // model != null
            boolean modelJavaLangObjectNull = false;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setPassword(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private android.databinding.InverseBindingListener etInviteRegisterandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.inviteCode
            //         is model.setInviteCode((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(etInviteRegister);
            // localize variables for thread safety
            // model
            com.weyko.shops.login.RegisterBean.RestiterSubmit model = mModel;
            // model != null
            boolean modelJavaLangObjectNull = false;
            // model.inviteCode
            java.lang.String modelInviteCode = null;



            modelJavaLangObjectNull = (model) != (null);
            if (modelJavaLangObjectNull) {




                model.setInviteCode(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public ActivityRegisterBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.cetAccountRegister = (com.weyko.shops.view.DrawableClickableEditText) bindings[4];
        this.cetCodeRegister = (com.weyko.shops.view.DrawableClickableEditText) bindings[2];
        this.cetCodeRegister.setTag(null);
        this.cetPwdRegister = (com.weyko.shops.view.DrawableClickableEditText) bindings[1];
        this.cetPwdRegister.setTag(null);
        this.etInviteRegister = (android.widget.EditText) bindings[3];
        this.etInviteRegister.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCodeRegister = (android.widget.TextView) bindings[5];
        this.tvRegisterRegister = (com.weyko.shops.view.CircularProgress.CircularProgressButton) bindings[6];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
            case BR.model :
                setModel((com.weyko.shops.login.RegisterBean.RestiterSubmit) variable);
                return true;
        }
        return false;
    }

    public void setModel(com.weyko.shops.login.RegisterBean.RestiterSubmit Model) {
        this.mModel = Model;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public com.weyko.shops.login.RegisterBean.RestiterSubmit getModel() {
        return mModel;
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
        java.lang.String modelPassword = null;
        com.weyko.shops.login.RegisterBean.RestiterSubmit model = mModel;
        java.lang.String modelSecurityCode = null;
        java.lang.String modelInviteCode = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (model != null) {
                    // read model.password
                    modelPassword = model.getPassword();
                    // read model.securityCode
                    modelSecurityCode = model.getSecurityCode();
                    // read model.inviteCode
                    modelInviteCode = model.getInviteCode();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.cetCodeRegister, modelSecurityCode);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.cetPwdRegister, modelPassword);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.etInviteRegister, modelInviteCode);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.cetCodeRegister, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, cetCodeRegisterandroidTextAttrChanged);
            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.cetPwdRegister, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, cetPwdRegisterandroidTextAttrChanged);
            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etInviteRegister, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etInviteRegisterandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityRegisterBinding>inflate(inflater, com.weyko.shops.R.layout.activity_register, root, attachToRoot, bindingComponent);
    }
    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_register, null, false), bindingComponent);
    }
    public static ActivityRegisterBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityRegisterBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_register_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityRegisterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): model
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}