package com.demo.ui.tab;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.demo.ui.R;
import com.demo.ui.activity.ActivityToolBarWithTab;

import java.util.ArrayList;
import java.util.List;

public class TabPageMgr {
    private final String TAG = TabPageMgr.class.getSimpleName();

    public static final int TAB_1 = 0;
    public static final int TAB_2 = 1;
    public static final int TAB_3 = 2;

    private int mCurrTab;
    private ActivityToolBarWithTab mActivity;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private MyPagerAdapter mMyPagerAdapter;

    private Menu mMenu;

    public TabPageMgr(ActivityToolBarWithTab activity) {
        mActivity = activity;
        mActivity.setTitle(activity.getClass().getSimpleName());
    }

    public void initViews() {
        mTabLayout = (TabLayout)mActivity.findViewById(R.id.tabs);
        mViewPager = (ViewPager)mActivity.findViewById(R.id.view_pager);

        List<String> titleList = new ArrayList<>();
        List<View> pageViewList = new ArrayList<>();

        titleList.add(mActivity.getString(R.string.tab_1));
        titleList.add(mActivity.getString(R.string.tab_2));
        titleList.add(mActivity.getString(R.string.tab_3));

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titleList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titleList.get(i)));
            if (i == TAB_1) {
                pageViewList.add(new View(mActivity)); // LayoutInflater.from(mActivity).inflate(xxx)
            } else if (i == TAB_2) {
                pageViewList.add(new View(mActivity));
            } else if (i == TAB_3) {
                pageViewList.add(new View(mActivity));
            }
        }

        mMyPagerAdapter = new MyPagerAdapter(titleList, pageViewList);
        mViewPager.setAdapter(mMyPagerAdapter);
        mViewPager.setCurrentItem(TAB_2, false);
        mCurrTab = TAB_2;

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mActivity.invalidateOptionsMenu();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getText().toString().equals(mActivity.getString(R.string.tab_1))) {
                } else if (tab.getText().toString().equals(mActivity.getString(R.string.tab_2))) {
                } else if (tab.getText().toString().equals(mActivity.getString(R.string.tab_3))) {
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public View getPageViewAt(int index) {
        return mMyPagerAdapter.getViewAt(index);
    }

    public void setCurrentPage(int index) {
        mViewPager.setCurrentItem(index, true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        mActivity.getMenuInflater().inflate(R.menu.toolbar_with_tab, menu);
        mMenu = menu;
        return true;
    }

    // Every time popup menu will call it!!
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_1) {
            return true;
        } else if (item.getItemId() == R.id.action_2) {
            return true;
        } else if (item.getItemId() == R.id.action_3) {
            return true;
        }

        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return false;
    }

    public static class MyPagerAdapter extends PagerAdapter {
        private List<String> titleList;
        private List<View> viewList;

        public MyPagerAdapter(List<String> titleList, List<View> viewList) {
            this.titleList = titleList;
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public View getViewAt(int index) {
            return viewList.get(index);
        }
    }
}
