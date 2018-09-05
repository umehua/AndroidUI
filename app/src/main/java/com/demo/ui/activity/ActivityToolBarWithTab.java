package com.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.demo.ui.R;
import com.demo.ui.tab.TabPageMgr;

public class ActivityToolBarWithTab extends AppCompatActivity {

    private TabPageMgr mTabPageMgr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_with_tab);
        initToolbar();
        initTabPage();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initTabPage() {
        mTabPageMgr = new TabPageMgr(this);
        mTabPageMgr.initViews();
    }

    @Override
    public void onBackPressed() {
        if (mTabPageMgr.onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mTabPageMgr.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mTabPageMgr.onPrepareOptionsMenu(menu)) {
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mTabPageMgr.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mTabPageMgr.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
