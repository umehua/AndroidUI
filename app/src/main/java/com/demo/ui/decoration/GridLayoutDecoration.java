package com.demo.ui.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridLayoutDecoration extends RecyclerView.ItemDecoration {

    private int spaceInPixel;
    private final Drawable divider;

    public GridLayoutDecoration(Drawable divier, int spaceInPixel) {
        this.divider = divier;
        this.spaceInPixel = spaceInPixel;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left;
        int right;
        int top;
        int bottom;

        top = parent.getPaddingTop();
        bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            left = child.getRight() + params.rightMargin;
            right = left + spaceInPixel;
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.set(spaceInPixel/2, spaceInPixel/2, spaceInPixel/2, spaceInPixel/2);
    }
}
