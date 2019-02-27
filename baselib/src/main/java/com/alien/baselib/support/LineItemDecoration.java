package com.alien.baselib.support;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LineItemDecoration extends RecyclerView.ItemDecoration {

    private ColorDrawable mDrawable;
    private int leftRight;
    private int topBottom;

    public LineItemDecoration(int leftRight, int topBottom, @ColorInt int color){
        this.leftRight = leftRight;
        this.topBottom = topBottom;
        this.mDrawable = new ColorDrawable(color);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LinearLayoutManager layoutManager = null;
        if (parent.getLayoutManager() instanceof LinearLayoutManager)
            layoutManager = (LinearLayoutManager) parent.getLayoutManager();

        if (layoutManager == null || layoutManager.getChildCount() == 0)
            return;


        int childCount = parent.getChildCount();
        int left;
        int right;
        int top;
        int bottom;
        int i = 0;
        while (i < childCount - 1){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                int center = (layoutManager.getTopDecorationHeight(child) - topBottom) / 2;
                left = layoutManager.getLeftDecorationWidth(child);
                right = parent.getWidth() - layoutManager.getLeftDecorationWidth(child);
                top = child.getBottom() + params.bottomMargin + center;
                bottom = top + topBottom;
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }else{
                int center = (layoutManager.getLeftDecorationWidth(child) - leftRight) / 2;
                left = child.getRight() + params.rightMargin + center;
                right = left + leftRight;
                top = layoutManager.getTopDecorationHeight(child);
                bottom = parent.getHeight() - layoutManager.getTopDecorationHeight(child);
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
            i++;
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(leftRight, topBottom, 0,0);
    }
}
