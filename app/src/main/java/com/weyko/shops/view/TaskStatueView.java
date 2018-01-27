package com.weyko.shops.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseApplication;

/**
 * Description:
 * Created  by: weyko on 2017/7/1.
 */
public class TaskStatueView extends View {
    /**
     * 默认0
     *
     * 已发布/接单
     */
    public final static int STATUE_SENED=1;
    /**
     * 已接单/通知付款
     */
    public final static int STATUE_GOT=STATUE_SENED+1;
    /**
     * 确认付款/待付款
     */
    public final static int STATUE_PAY=STATUE_GOT+1;
    /**
     * 订单完成
     */
    public final static int STATUE_COMPLETE=STATUE_PAY+1;
    private int colorChecked;
    private Paint circle0;
    private Paint line0;
    private Paint circle1;
    private Paint line1;
    private Paint circle2;
    private Paint text;
    private float startX=100;
    private float startY=100;
    private int circleRadis=35;
    private int lineWidth=200;
    private int lineHeight=8;
    private int dotX=1;
    private int textHeight=30;
    private int taskStatue=0;
    public TaskStatueView(Context context) {
        this(context,null);
    }

    public TaskStatueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawLine(canvas);
        drawText(canvas);
    }

    /**
     * 这是任务状态
     * @param taskStatue
     */
    public void setTaskStatue(int taskStatue){
        switch (taskStatue){
            case STATUE_SENED:
                line0.setStyle(Paint.Style.FILL);
                break;
            case STATUE_GOT:
                line0.setStyle(Paint.Style.FILL);
                circle1.setStyle(Paint.Style.FILL);
                break;
            case STATUE_PAY:
                line0.setStyle(Paint.Style.FILL);
                circle1.setStyle(Paint.Style.FILL);
                line1.setStyle(Paint.Style.FILL);
                break;
            case STATUE_COMPLETE:
                line0.setStyle(Paint.Style.FILL);
                circle1.setStyle(Paint.Style.FILL);
                line1.setStyle(Paint.Style.FILL);
                circle2.setStyle(Paint.Style.FILL);
                break;
            default:
                break;
        }
        invalidate();
    }
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(startX,startY,circleRadis,circle0);
        canvas.drawCircle(startX+lineWidth+2*circleRadis,startY,circleRadis,circle1);
        canvas.drawCircle(startX+2*lineWidth+4*circleRadis,startY,circleRadis,circle2);
    }
    private void drawLine(Canvas canvas) {
        canvas.drawRect(startX+circleRadis,startY-lineHeight,startX+circleRadis+lineWidth+dotX,startY+lineHeight,line0);
        canvas.drawRect(startX+3*circleRadis+lineWidth,startY-lineHeight,startX+3*circleRadis+2*lineWidth+dotX,startY+lineHeight,line1);
    }
    private void drawText(Canvas canvas){
        float endX=startX-circleRadis/2;
        float endY=startY+circleRadis+10;
        canvas.drawText("已",endX,endY+textHeight,text);
        canvas.drawText("发",endX,endY+2*textHeight,text);
        canvas.drawText("布",endX,endY+3*textHeight,text);
        float endX1=startX+lineWidth+2*circleRadis-circleRadis/2;
        canvas.drawText("已",endX1,endY+textHeight,text);
        canvas.drawText("接",endX1,endY+2*textHeight,text);
        canvas.drawText("单",endX1,endY+3*textHeight,text);
        float endX2=startX+2*lineWidth+4*circleRadis-circleRadis/2;
        canvas.drawText("已",endX2,endY+textHeight,text);
        canvas.drawText("完",endX2,endY+2*textHeight,text);
        canvas.drawText("成",endX2,endY+3*textHeight,text);
    }
    private void init(){
        int padding= getResources().getDimensionPixelOffset(R.dimen.activity_padding_top);
        textHeight= getResources().getDimensionPixelOffset(R.dimen.text_size_3);
        lineWidth= (BaseApplication.getInstance().getScreenWith()-6*circleRadis-2*padding)/2;
        colorChecked=getResources().getColor(R.color.colorPrimary);
        circle0=new Paint();
        circle0.setAntiAlias(true);
        circle0.setStyle(Paint.Style.FILL);
        circle0.setColor(colorChecked);
        line0=new Paint();
        line0.setColor(colorChecked);
        line0.setAntiAlias(true);
        line0.setStyle(Paint.Style.STROKE);
        circle1=new Paint();
        circle1.setAntiAlias(true);
        circle1.setStyle(Paint.Style.STROKE);
        circle1.setColor(colorChecked);
        line1=new Paint();
        line1.setAntiAlias(true);
        line1.setStyle(Paint.Style.STROKE);
        line1.setColor(colorChecked);
        circle2=new Paint();
        circle2.setAntiAlias(true);
        circle2.setStyle(Paint.Style.STROKE);
        circle2.setColor(colorChecked);
        text=new Paint();
        text.setAntiAlias(true);
        text.setColor(colorChecked);
        text.setTextSize(textHeight);
    }
   /* @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }*/
    private int measureHeight(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = (int) getWidth();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 75;//根据自己的需要更改
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }
}
