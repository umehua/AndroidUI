package com.demo.ui.touch;

import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper;

public class MyItemTouchHelper extends ItemTouchHelper {

    private MyItemTouchHelpCallback mCallback;

    public MyItemTouchHelper(@NonNull MyItemTouchHelpCallback callback) {
        super(callback);
        mCallback = callback;
    }

    public void setDragEnable(boolean canDrag) {
        mCallback.setDragEnable(canDrag);
    }

    public void setSwipeEnable(boolean canSwipe) {
        mCallback.setSwipeEnable(canSwipe);
    }
}
