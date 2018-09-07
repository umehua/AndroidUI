package com.demo.ui.adapter.expandable.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.ui.R;
import com.demo.ui.adapter.expandable.data.CardSet;
import com.demo.ui.adapter.expandable.data.Category;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CategoryAdapter extends ExpandableRecyclerViewAdapter<CategoryVH, CardSetVH> {

    public CategoryAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public CategoryVH onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public CardSetVH onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_set, parent, false);
        return new CardSetVH(view);
    }

    @Override
    public void onBindGroupViewHolder(CategoryVH holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setCategory((Category)group, flatPosition);
    }

    @Override
    public void onBindChildViewHolder(CardSetVH holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final CardSet cardSet = ((Category) group).getItems().get(childIndex);
        holder.setCardSet(cardSet, childIndex);
    }
}