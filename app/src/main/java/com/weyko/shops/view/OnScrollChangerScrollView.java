package com.weyko.shops.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.weyko.shops.util.ShowUtil;

/**
 * Description: 监听滑动scrollview
 * Created  by: weyko on 2017/7/7.
 */

public class OnScrollChangerScrollView extends ScrollView {
    public OnScrollChangerScrollView(Context context) {
        super(context);
    }

    public OnScrollChangerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnScrollChangerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private OnScrollViewChangerListener onScrollViewChangerListener;

    public void setOnScrollViewChangerListener(OnScrollViewChangerListener onScrollViewChangerListener) {
        this.onScrollViewChangerListener = onScrollViewChangerListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ShowUtil.d("weyko","t="+t+" oldt="+oldt);
        if(onScrollViewChangerListener!=null){
            onScrollViewChangerListener.onScrollChanged(t,oldt);
        }
    }
    public interface  OnScrollViewChangerListener{
        public void onScrollChanged(int top, int oldTop);
    }
}
