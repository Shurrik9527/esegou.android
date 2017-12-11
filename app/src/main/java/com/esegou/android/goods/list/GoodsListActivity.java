package com.esegou.android.goods.list;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.esegou.android.R;

public class GoodsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        GoodsListFragment goodsListFragment = (GoodsListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fl_test);

        if (goodsListFragment == null) {
            goodsListFragment = GoodsListFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_test, goodsListFragment);
            transaction.commit();
        }

        new GoodsListPresenter(goodsListFragment);
    }
}
