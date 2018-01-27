package com.weyko.shops.base;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weyko.shops.R;
import com.weyko.shops.util.PerfectClickListener;

/**
 * Description:公用对话框
 * Created  by: weyko on 2017/8/1.
 */

public class BaseDialog extends Dialog {
    private FragmentActivity mContext;
    private LinearLayout ll_root_dialog;
    private TextView tv_sure_dialog,tv_cancal_dialog,tv_msg_dialog,tv_hint_dialog;
    private View.OnClickListener onSureClickListener;
    public BaseDialog(FragmentActivity context) {
        super(context, R.style.Theme_Light_FullScreenDialogAct);
        mContext = context;
        setContentView(R.layout.layout_dialog);
        initViews();
        initEvents();
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    /**
     * 设置确认监听事件
     * @param onSureClickListener
     */
    public void setOnSureClick(View.OnClickListener onSureClickListener){
        this.onSureClickListener=onSureClickListener;
    }

    /**
     * 修改确认按钮文案
     */
    public void setSureText(String sureText){
        tv_sure_dialog.setText(sureText);
    }

    /**
     * 显示消息内容
     * @param msg
     */
    public void setMsg(String msg){
        tv_msg_dialog.setText(msg);
    }
    /**
     * 显示提示内容
     * @param msg
     */
    public void setHint(String msg){
        if(TextUtils.isEmpty(msg)){
            tv_hint_dialog.setVisibility(View.GONE);
        }else{
            tv_hint_dialog.setVisibility(View.VISIBLE);
            tv_hint_dialog.setText(msg);
        }
    }

    private void initEvents() {
        tv_sure_dialog.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                dismiss();
                if(onSureClickListener!=null){
                    onSureClickListener.onClick(v);
                }
            }
        });
        tv_cancal_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void initViews() {
        tv_sure_dialog= (TextView) this.findViewById(R.id.tv_sure_dialog);
        tv_cancal_dialog= (TextView) this.findViewById(R.id.tv_cancal_dialog);
        tv_msg_dialog= (TextView) this.findViewById(R.id.tv_msg_dialog);
        tv_hint_dialog= (TextView) this.findViewById(R.id.tv_hint_dialog);
        ll_root_dialog= (LinearLayout) this.findViewById(R.id.ll_root_dialog);
        // 获取屏幕宽高密度
        DisplayMetrics metric = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metric);
        ll_root_dialog.setMinimumWidth(metric.heightPixels - 50);
    }
}
