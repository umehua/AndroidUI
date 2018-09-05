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
        private final int orientation;
        private int padding;

        public GridLayoutDecoration(int orientation, Drawable divier,
                                        int spaceInPixel, int padding) {
            this.divider = divier;
            this.spaceInPixel = spaceInPixel;
            this.orientation = orientation;
            this.padding = padding;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            c.drawColor(0xffffffff);

            int left;
            int right;
            int top;
            int bottom;

            if (orientation == LinearLayoutManager.HORIZONTAL) {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params =
                            (RecyclerView.LayoutParams) child.getLayoutParams();
                    left = child.getRight() + params.rightMargin;
                    right = left + spaceInPixel;
                    divider.setBounds(left, top, right, bottom);
                    divider.draw(c);
                }
            } else {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();

                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params =
                            (RecyclerView.LayoutParams) child.getLayoutParams();
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + spaceInPixel;
                    divider.setBounds(left + padding, top, right - padding, bottom);
                    divider.draw(c);
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                outRect.set(spaceInPixel/2, 0, spaceInPixel/2, 0);
            } else {
                outRect.set(0, spaceInPixel/2, 0, spaceInPixel/2);
            }
        }
}