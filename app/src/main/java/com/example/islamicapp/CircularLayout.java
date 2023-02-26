package com.example.islamicapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CircularLayout extends RelativeLayout {

    private static final int CIRCULAR_LAYOUT_CHILD_ANGLE = 360 / 7;
    private List<View> mChildViews = new ArrayList<>();
    private int mChildSize;

    public CircularLayout(Context context) {
        super(context);
        init();
    }

    public CircularLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClipChildren(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);

        mChildSize = size / 4;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int centerX = getMeasuredWidth() / 2;
        int centerY = getMeasuredHeight() / 2;
        int radius = Math.min(centerX, centerY) - mChildSize;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int angle = CIRCULAR_LAYOUT_CHILD_ANGLE * i;
            int x = (int) (centerX + radius * Math.cos(Math.toRadians(angle)) - (child.getMeasuredWidth() / 2));
            int y = (int) (centerY + radius * Math.sin(Math.toRadians(angle)) - (child.getMeasuredHeight() / 2));

            child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        mChildViews.add(child);
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        mChildViews.remove(view);
    }

    public List<View> getChildViews() {
        return mChildViews;
    }
}

