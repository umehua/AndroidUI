package com.demo.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    public static final int TO_VIEW_TAG = -982171;

    protected List<Map<String, Object>> mDataset;
    protected List<Map<String, Object>> mOriginDataset;
    protected List<Map<String, Object>> mFilterDataset;
    protected int mLayout;
    protected String[] mFrom;
    protected int[] mTo;
    protected boolean mHasHead;
    protected boolean mHasTail;

    protected  KeyWordFilter mFilter;

    public MyAdapter(List<Map<String, Object>> data, int resource, String[] from, int[] to) {
        mOriginDataset = mDataset = data;
        mLayout = resource;
        mFrom = from;
        mTo = to;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);
        RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(v) {};
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Map<String, Object> m = mDataset.get(position);
        for (int i = 0; i < mTo.length; i++) {

            if (mTo[i] == TO_VIEW_TAG) {
                holder.itemView.setTag(m.get(mFrom[i]));
                continue;
            }

            View v = holder.itemView.findViewById(mTo[i]);
            Object value = m.get(mFrom[i]);

            if (value == null) {
                v.setVisibility(View.GONE);
                continue;
            } else {
                v.setVisibility(View.VISIBLE);
            }

            if (v instanceof CheckBox || v instanceof AppCompatCheckBox) {
                if (v instanceof CheckBox) {
                    ((CheckBox) v).setChecked((boolean) value);
                } else {
                    ((AppCompatCheckBox) v).setChecked((boolean) value);
                }
            } else if (v instanceof ImageView || v instanceof AppCompatImageView) {
                if (v instanceof ImageView) {
                    if (value instanceof Integer) {
                        ((ImageView) v).setImageResource((int) value);
                    } else if (value instanceof Drawable) {
                        ((ImageView) v).setImageDrawable((Drawable) value);
                    }
                } else {
                    if (value instanceof Integer) {
                        ((AppCompatImageView) v).setImageResource((int) value);
                    } else if (value instanceof Drawable) {
                        ((AppCompatImageView) v).setImageDrawable((Drawable) value);
                    }
                }
            } else if (v instanceof RadioButton || v instanceof AppCompatRadioButton) {
                if (v instanceof RadioButton) {
                    if (value instanceof Boolean) {
                        ((RadioButton) v).setChecked((boolean) value);
                    }
                } else {
                    if (value instanceof Boolean) {
                        ((AppCompatRadioButton) v).setChecked((boolean) value);
                    }
                }
            } else if (v instanceof TextView || v instanceof AppCompatTextView) {
                String text = "";
                if (value instanceof Integer) {
                    text = v.getContext().getString((int) value);
                } else if (value instanceof String){
                    text = (String)value;
                }

                if (v instanceof TextView) {
                    ((TextView)v).setText(text);
                } else {
                    ((AppCompatTextView) v).setText(text);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new KeyWordFilter();
        }
        return mFilter;
    }

    public void setHasHeadTail(boolean hasHead, boolean hasTail) {
        mHasHead = hasHead;
        mHasTail = hasTail;
    }

    public void restoreOriginDataSet() {
        mDataset = mOriginDataset;
        notifyDataSetChanged();
    }

    public List<Map<String, Object>> getDataset() {
        return mDataset;
    }

    public List<Map<String, Object>> getOriginDataset() {
        return mOriginDataset;
    }

    class KeyWordFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults res = new FilterResults();

            mFilterDataset = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < mOriginDataset.size(); i++) {
                if ((mHasHead && i == 0)
                        || (mHasTail && i == mOriginDataset.size() - 1)) {
                    continue;
                }
                Map<String, Object> map = mOriginDataset.get(i);
                for (Object key : map.keySet()) {
                    Object value = map.get(key);
                    if (value instanceof String) {
                        String text = (String)value;
                        if (text.contains(constraint)) {
                            mFilterDataset.add(map);
                            break;
                        }
                    }
                }
            }

            res.count = mFilterDataset.size();
            res.values = mFilterDataset;

            return res;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataset = mFilterDataset;
            notifyDataSetChanged();
        }
    }
}
