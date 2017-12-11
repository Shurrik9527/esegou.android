package com.esegou.android.test;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.esegou.android.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        TestFragment taskDetailFragment = (TestFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fl_test);

        if (taskDetailFragment == null) {
            taskDetailFragment = TestFragment.newInstance("", "");

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_test, taskDetailFragment);
            transaction.commit();
        }

        new TestPresenter(taskDetailFragment);
    }
}
