package com.weyko.shops.databinding;
import com.weyko.shops.R;
import com.weyko.shops.BR;
import android.view.View;
public class ActivityLoginBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.til_phone_login, 2);
        sViewsWithIds.put(R.id.cet_input_account, 3);
        sViewsWithIds.put(R.id.til_pwd_login, 4);
        sViewsWithIds.put(R.id.tv_fpwd_login, 5);
        sViewsWithIds.put(R.id.cpb_login, 6);
        sViewsWithIds.put(R.id.tv_register_login, 7);
        sViewsWithIds.put(R.id.iv_qq_login, 8);
        sViewsWithIds.put(R.id.iv_wexin_login, 9);
    }
    // views
    public final com.weyko.shops.view.DrawableClickableEditText cetInputAccount;
    public final com.weyko.shops.view.DrawableClickableEditText cetInputPwd;
    public final com.weyko.shops.view.CircularProgress.CircularProgressButton cpbLogin;
    public final android.widget.ImageView ivQqLogin;
    public final android.widget.ImageView ivWexinLogin;
    private final android.widget.LinearLayout mboundView0;
    public final android.support.design.widget.TextInputLayout tilPhoneLogin;
    public final android.support.design.widget.TextInputLayout tilPwdLogin;
    public final android.widget.TextView tvFpwdLogin;
    public final android.widget.TextView tvRegisterLogin;
    // variables
    private com.weyko.shops.login.LoginModel mModel;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private android.databinding.InverseBindingListener cetInputPwdandroidTextAttrChanged = new android.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of model.password
            //         is model.setPassword((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = android.databinding.adapters.TextViewBindingAdapter.getTextString(cetInputPwd);
            // localize variables for thread safety
            // model
            com.weyko.shops.login.LoginModel model = mModel;
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

    public ActivityLoginBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.cetInputAccount = (com.weyko.shops.view.DrawableClickableEditText) bindings[3];
        this.cetInputPwd = (com.weyko.shops.view.DrawableClickableEditText) bindings[1];
        this.cetInputPwd.setTag(null);
        this.cpbLogin = (com.weyko.shops.view.CircularProgress.CircularProgressButton) bindings[6];
        this.ivQqLogin = (android.widget.ImageView) bindings[8];
        this.ivWexinLogin = (android.widget.ImageView) bindings[9];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tilPhoneLogin = (android.support.design.widget.TextInputLayout) bindings[2];
        this.tilPwdLogin = (android.support.design.widget.TextInputLayout) bindings[4];
        this.tvFpwdLogin = (android.widget.TextView) bindings[5];
        this.tvRegisterLogin = (android.widget.TextView) bindings[7];
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
                setModel((com.weyko.shops.login.LoginModel) variable);
                return true;
        }
        return false;
    }

    public void setModel(com.weyko.shops.login.LoginModel Model) {
        this.mModel = Model;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public com.weyko.shops.login.LoginModel getModel() {
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
        com.weyko.shops.login.LoginModel model = mModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (model != null) {
                    // read model.password
                    modelPassword = model.getPassword();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.cetInputPwd, modelPassword);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.cetInputPwd, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, cetInputPwdandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityLoginBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityLoginBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityLoginBinding>inflate(inflater, com.weyko.shops.R.layout.activity_login, root, attachToRoot, bindingComponent);
    }
    public static ActivityLoginBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityLoginBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.weyko.shops.R.layout.activity_login, null, false), bindingComponent);
    }
    public static ActivityLoginBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityLoginBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_login_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityLoginBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): model
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}