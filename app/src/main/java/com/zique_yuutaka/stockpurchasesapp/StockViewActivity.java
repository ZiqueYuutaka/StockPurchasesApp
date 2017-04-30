package com.zique_yuutaka.stockpurchasesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.UUID;

import dao.StockDAO;
import fragments.LandingFragment;
import fragments.StockViewFragment;

/**
 * Created by Zique Yuutaka on 4/30/2017.
 */

public class StockViewActivity extends AppCompatActivity {

    private static final String DEBUG = "STOCK_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.stock_view_fragment);

        if(fragment == null){
            fragment = new StockViewFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, StockViewActivity.class);

        return intent;
    }
}
