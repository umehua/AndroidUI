package com.demo.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.demo.ui.R;
import com.demo.ui.adapter.MyAdapter;
import com.demo.ui.decoration.LinearLayoutDecoration;
import com.demo.ui.touch.MyItemTouchHelpCallback;
import com.demo.ui.touch.MyItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityDragDropRecycler extends AppCompatActivity {
    private RecyclerView mRecycler;
    private MyAdapter mAdapter;
    private MyItemTouchHelper mTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_linear);

        initViews();
    }

    private void initViews() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);

        mRecycler.addItemDecoration(new LinearLayoutDecoration(
                LinearLayoutManager.VERTICAL,
                new ColorDrawable(0x1a4e4e4e),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                        getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                        getResources().getDisplayMetrics())));

        LinearLayoutManager layoutMgr = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutMgr);

        List<Map<String, Object>> data = new ArrayList<>();
        final String[] FROM = new String[] {"tv_title", "tv_content", "image_view", "checkbox", "tag"};
        final int[] TO = new int[] {R.id.tv_title, R.id.tv_content, R.id.image_view, R.id.checkbox, MyAdapter.TO_VIEW_TAG};

        String titles[] = getResources().getStringArray(R.array.recycler_drag_drop_list);
        for (int i = 0; i <titles.length; i++) {
            Map<String, Object> m = new HashMap<>();
            m.put(FROM[0], titles[i]);
            m.put(FROM[1], null);
            m.put(FROM[2], android.R.drawable.ic_menu_more);
            m.put(FROM[3], null);
            m.put(FROM[4], null);
            data.add(m);
        }

        mAdapter = new MyAdapter(data, R.layout.item_linear, FROM, TO);
        mRecycler.setAdapter(mAdapter);

        mRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                view.findViewById(R.id.image_view).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mTouchHelper.setDragEnable(true);
                        mTouchHelper.setSwipeEnable(true);
                        return true;
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });

        mTouchHelper = new MyItemTouchHelper(new MyItemTouchHelpCallback(mTouchCb));
        mTouchHelper.attachToRecyclerView(mRecycler);
    }

    private MyItemTouchHelpCallback.OnItemTouchCallbackListener mTouchCb = new MyItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwiped(int adapterPosition) {
            mAdapter.getDataset().remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
            mTouchHelper.setDragEnable(false);
            mTouchHelper.setSwipeEnable(false);
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if (mAdapter.getDataset().size() > 0) {
                Collections.swap(mAdapter.getDataset(), srcPosition, targetPosition);
                mAdapter.notifyItemMoved(srcPosition, targetPosition);
                mTouchHelper.setDragEnable(false);
                mTouchHelper.setSwipeEnable(false);
                return true;
            }
            return false;
        }
    };
}
