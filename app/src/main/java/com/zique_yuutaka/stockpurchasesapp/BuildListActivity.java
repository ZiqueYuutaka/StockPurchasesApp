package com.zique_yuutaka.stockpurchasesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import fragments.BuildListFragment;
import fragments.LandingFragment;

/**
 * Created by Zique Yuutaka on 4/18/2017.
 */

public class BuildListActivity extends AppCompatActivity {
    private static final String DEBUG = "BUILD_LIST_ACTIVITY";
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.build_list_fragment);

        if(fragment == null){
            fragment = new BuildListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public static Intent newIntent(Context packageContext){
        Intent i = new Intent(packageContext, BuildListActivity.class);
        return i;
    }
}
