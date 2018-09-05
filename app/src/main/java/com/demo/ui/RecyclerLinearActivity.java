package com.demo.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.demo.ui.activity.ActivityToolBarWithTab;
import com.demo.ui.activity.ActivityDragDropRecycler;
import com.demo.ui.activity.ActivityGridRecycler;
import com.demo.ui.adapter.MyAdapter;
import com.demo.ui.decoration.LinearLayoutDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerLinearActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private Map<String, int[]> mRecyclerPosMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_linear);

        initViews();
    }

    private void initViews() {
        mRecycler = (RecyclerView)findViewById(R.id.recycler_view);

        mRecycler.addItemDecoration(new LinearLayoutDecoration(
                LinearLayoutManager.VERTICAL,
                new ColorDrawable(0x1a4e4e4e),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                        getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                        getResources().getDisplayMetrics())));

        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);

        List<Map<String, Object>> data = new ArrayList<>();
        final String[] FROM = new String[] {"tv_title", "tv_content", "checkbox", "tag"};
        final int[] TO = new int[] {R.id.tv_title, R.id.tv_content, R.id.checkbox, MyAdapter.TO_VIEW_TAG};

        String titles[] = getResources().getStringArray(R.array.recycler_list);
        for (int i = 0; i <titles.length; i++) {
            Map<String, Object> m = new HashMap<>();
            m.put(FROM[0], titles[i]);
            m.put(FROM[1], null);
            m.put(FROM[2], null);
            m.put(FROM[3], null);
            data.add(m);
        }

        mAdapter = new MyAdapter(data, R.layout.item_linear, FROM, TO);
        mRecycler.setAdapter(mAdapter);

        mRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                view.findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = mRecycler.getChildAdapterPosition((View)view.getParent());
                        if (index == 0) {
                            Intent i = new Intent(getApplicationContext(), ActivityGridRecycler.class);
                            startActivity(i);
                        } else if (index == 1) {
                            Intent i = new Intent(getApplicationContext(), ActivityDragDropRecycler.class);
                            startActivity(i);
                        } else if (index == 2) {
                            Intent i = new Intent(getApplicationContext(), ActivityToolBarWithTab.class);
                            startActivity(i);
                        }
                        Toast.makeText(getApplicationContext(), "click : "+ index, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });
    }

    private void saveRecyclerPosition() {
        if (mLayoutManager == null || mAdapter == null || mAdapter.getDataset().size() == 0) {
            return;
        }
        View topView = mLayoutManager.getChildAt(0);
        if (topView == null) {
            return;
        }
        int pos[] = new int[2];
        pos[0] = mLayoutManager.getPosition(topView);
        pos[1] = topView.getTop();
        mRecyclerPosMap.put("save_pos_1", pos);
    }

    private void restoreFolderPosition() {
        if (mLayoutManager == null || mAdapter == null || mAdapter.getDataset().size() == 0) {
            return;
        }
        int pos[] = mRecyclerPosMap.get("save_pos_1");
        if (pos != null) {
            mLayoutManager.scrollToPositionWithOffset(pos[0], pos[1]);
        }
    }
}
