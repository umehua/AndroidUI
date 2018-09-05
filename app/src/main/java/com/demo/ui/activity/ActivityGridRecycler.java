package com.demo.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.demo.ui.R;
import com.demo.ui.adapter.MyAdapter;
import com.demo.ui.decoration.LinearLayoutDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityGridRecycler extends AppCompatActivity {
    private RecyclerView mRecycler;

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

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecycler.setLayoutManager(layoutManager);

        List<Map<String, Object>> data = new ArrayList<>();
        final String[] FROM = new String[]{"image_view", "tag"};
        final int[] TO = new int[]{R.id.image_view, MyAdapter.TO_VIEW_TAG};

        for (int i = 0; i < 6; i++) {
            Map<String, Object> m = new HashMap<>();
            m.put(FROM[0], R.drawable.ic_launcher_background);
            data.add(m);
        }

        MyAdapter mAdapter = new MyAdapter(data, R.layout.item_grid, FROM, TO);
        mRecycler.setAdapter(mAdapter);

        mRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                view.findViewById(R.id.image_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = mRecycler.getChildAdapterPosition((View) view.getParent());
                        Toast.makeText(getApplicationContext(), "click : " + index, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });
    }
}
