package com.weyko.shops.manager;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.view.DrawableClickableEditText;

/**
 * Description:
 * Created  by: weyko on 2017/6/22.
 */

public class InputManager{
    public static final int INPUT_TYPE_CLEAR=0;
    public static final int INPUT_TYPE_PWD=INPUT_TYPE_CLEAR+1;
    public void manageInputView(final DrawableClickableEditText inputView, final int inputType, final int drawableLeft){
        if(inputView==null)return;
        inputView.setDrawableRightListener(new DrawableClickableEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                if(inputType==INPUT_TYPE_CLEAR){
                    inputView.setText("");
                }else if(inputType==INPUT_TYPE_PWD) {
                    Object tag = view.getTag();
                    boolean isChecked = tag != null;
                    //设置密码是否明文
                    inputView.setTransformationMethod(isChecked ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                    //设置右边图标是否选中
                    inputView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, 0, isChecked ? R.mipmap.icon_eye : R.mipmap.icon_eye_checked, 0);
                    view.setTag(isChecked ? null : "");
                }
            }
        });
        inputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(inputType==INPUT_TYPE_PWD){
                    Object tag = inputView.getTag();
                    int rightDrawable=tag!=null?R.mipmap.icon_eye_checked:R.mipmap.icon_eye;
                    inputView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,0,charSequence.length()>0?rightDrawable:0,0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
