package com.astro.weatherappcustom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
    private Paint paint;
    private static String TAG = "CustomView";
    private int radius = 100;
    private int color = Color.GREEN;
    private boolean pressed = false;
    View.OnClickListener listener;

    public CustomView(Context context) {
        super(context);
        Log.i(TAG, "Constructor");
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initAttr(Context context, AttributeSet attrs)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomView);
        radius = typedArray.getResourceId(R.styleable.CustomView_cv_Radius, 150);
        color = typedArray.getResourceId(R.styleable.CustomView_cv_Color, Color.GREEN);
        typedArray.recycle();
    }

    @Override
    public void requestLayout() {
        Log.i(TAG, "requestLayout");
        super.requestLayout();
    }

    @Override
    protected void onAttachedToWindow() {
        Log.i(TAG, "onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Log.i(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b)
    {
        Log.i(TAG, "layout");
        super.layout(l,t,r,b);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        Log.i(TAG, "onLayout");
        super.onLayout(changed, l,t,r,b);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.i(TAG, "draw");
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        super.onDraw(canvas);
        int rad = pressed? radius/2 : radius;
        canvas.drawCircle(radius, radius, rad, paint);
    }

    @Override
    public void invalidate() {
        Log.i(TAG, "invalidate");
        super.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                invalidate();
                if(listener != null) listener.onClick(this);
                break;
            case MotionEvent.ACTION_UP:
                pressed = false;
                invalidate();
                break;
        }
        return true;
    }
}
