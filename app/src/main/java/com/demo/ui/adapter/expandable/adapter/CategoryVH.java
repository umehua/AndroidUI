package com.demo.ui.adapter.expandable.adapter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.ui.R;
import com.demo.ui.adapter.expandable.data.Category;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CategoryVH extends GroupViewHolder {

    private TextView mTextView;

    public CategoryVH(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_name);
    }

    public void setCategory(Category group, final int adapterPos) {
        if (group instanceof Category) {
            mTextView.setText(((Category)group).getName());
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mTextView.getContext(), "click "+ adapterPos, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
//        RotateAnimation rotate =
//                new RotateAnimation(360, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotate.setDuration(300);
//        rotate.setFillAfter(true);
//        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
//        RotateAnimation rotate =
//                new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotate.setDuration(300);
//        rotate.setFillAfter(true);
//        arrow.setAnimation(rotate);
    }
}
