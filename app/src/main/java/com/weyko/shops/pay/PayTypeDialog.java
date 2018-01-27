package com.weyko.shops.pay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.weyko.shops.R;

/**
 * 支付对话框
 * @ClassName: PayTypeDialog
 */
public class PayTypeDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private int layoutRes;
    private int mPaymentMethod = PayConfig.PAY_ALI;
    private PayTypeDialogListener mPayTypeDialogListener;
    private RadioGroup rg_payment_option;
    private ImageView iv_close;
    private TextView confirm_btn;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_alipay_payment) {
            caseCashAliBox();
        } else if (i == R.id.rb_wxpay_payment) {
            caseCashWXBox();
        }
    }

    public interface PayTypeDialogListener {
        void payTypeDialogCallback(int paymentMethod);
    }

    public void setListener(PayTypeDialogListener payTypeDialogListener) {
        this.mPayTypeDialogListener = payTypeDialogListener;
    }

    public PayTypeDialog(Activity activity) {
        super(activity, R.style.dialog_password);
    }

    public PayTypeDialog(Context context, int resLayout) {
        super(context, R.style.dialog_password);
        this.layoutRes = resLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (layoutRes == 0) {
            layoutRes = R.layout.dialog_payment_option;
        }
        this.setContentView(layoutRes);
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rg_payment_option = (RadioGroup) this.findViewById(R.id.rg_payment_option);
        iv_close = (ImageView) this.findViewById(R.id.iv_close);
        confirm_btn = (TextView) this.findViewById(R.id.confirm_btn);
        iv_close.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
        setCanceledOnTouchOutside(true);
        rg_payment_option.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int view_id = v.getId();
        switch (view_id) {
            case R.id.confirm_btn:
                dismiss();
                if (mPayTypeDialogListener != null) {
                    mPayTypeDialogListener.payTypeDialogCallback(mPaymentMethod);
                }
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    private void caseCashAliBox() {
        mPaymentMethod = PayConfig.PAY_ALI;
        rg_payment_option.check(R.id.rb_alipay_payment);
    }

    private void caseCashWXBox() {
        mPaymentMethod = PayConfig.PAY_WX;
        rg_payment_option.check(R.id.rb_wxpay_payment);
    }
}
