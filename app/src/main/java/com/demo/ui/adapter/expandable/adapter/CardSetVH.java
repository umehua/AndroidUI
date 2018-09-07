package com.demo.ui.adapter.expandable.adapter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.ui.R;
import com.demo.ui.adapter.expandable.data.CardSet;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CardSetVH extends ChildViewHolder {
    private TextView mTvName;
    private TextView mTvCount;

    public CardSetVH(View itemView) {
        super(itemView);
        mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        mTvCount = (TextView) itemView.findViewById(R.id.tv_count);
    }

    public void setCardSet(CardSet set, final int adapterPos) {
        mTvName.setText(set.getName());
        mTvCount.setText(String.valueOf(set.getCardCount()));
        mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mTvName.getContext(), "click "+ adapterPos, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
