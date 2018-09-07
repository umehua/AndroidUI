package com.demo.ui.adapter.expandable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.demo.ui.adapter.expandable.adapter.CategoryAdapter;
import com.demo.ui.adapter.expandable.data.Category;
import com.demo.ui.adapter.expandable.data.FakeDataGenerator;

public class CardSetView extends RecyclerView {

    public CardSetView(Context context) {
        super(context);
    }

    public CardSetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardSetView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initViews();
    }

    private void initViews() {
        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(layoutManager);

        CategoryAdapter adapter = new CategoryAdapter(FakeDataGenerator.makeCategories());
        setAdapter(adapter);

        adapter.toggleGroup(new Category("c2",
                FakeDataGenerator.makeC2CardSet()));
    }

}
