package com.zique_yuutaka.stockpurchasesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.DebugUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import fragments.LandingFragment;

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG = "***MAIN_ACTIVITY***";
    private static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.landing_fragment);

        if(fragment == null){
            fragment = new LandingFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        switch(item.getItemId()){
            case(R.id.create_list):
                Log.d(DEBUG, "Creating the list");
                break;
            case(R.id.view_list):
                Log.d(DEBUG, "Viewing the list");
                break;
            case(R.id.quit):
                Log.d(DEBUG, "Quitting the application");
                break;
        }

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            Log.d(DEBUG, "Settings selected");
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
