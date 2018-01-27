package com.weyko.shops.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.weyko.shops.R;

/**
 * Description:
 * Created  by: weyko on 2017/6/2.
 */

public class DrawableClickableEditText extends android.support.v7.widget.AppCompatEditText {
    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;

    public DrawableClickableEditText(Context context) {
        super(context);
    }

    public DrawableClickableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableClickableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int paddingLeft = 0;

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    private int paddingRight = 0;

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    private int drawableRight;

    public void setDrawableRight(int drawableRight) {
        this.drawableRight = drawableRight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
                if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width() - paddingRight)) {
                    if (this.drawableRight!=R.mipmap.icon_eye)
                        setText("");
                    if (mRightListener != null) {
                        mRightListener.onDrawableRightClick(this);
                    }
                    return false;
                }

                Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT];
                if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width() + paddingLeft)) {
                    if (mLeftListener != null)
                        mLeftListener.onDrawableLeftClick(this);
                    return false;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;

    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    public interface DrawableLeftListener {
        public void onDrawableLeftClick(View view);
    }

    public interface DrawableRightListener {
        public void onDrawableRightClick(View view);
    }

    public void setMaxLength(int maxlength) {
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlength)});
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        int right = drawableRight == 0 ? R.mipmap.icon_close : drawableRight;
        setCompoundDrawablesWithIntrinsicBounds(0, 0, text.length() > 0 ? right : 0, 0);
    }
}
