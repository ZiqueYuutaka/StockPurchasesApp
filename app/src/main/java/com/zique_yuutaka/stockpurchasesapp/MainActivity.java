package com.zique_yuutaka.stockpurchasesapp;

import android.content.Intent;
import android.os.Build;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.StockDAO;
import database.TreeDB;
import dataobject.Stock;
import fragments.LandingFragment;
import fragments.StockListFragment;

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG = "***MAIN_ACTIVITY***";
    private static FragmentManager fm;
    public static boolean dbExists;

    private Menu myMenu;

    TreeDB tree;

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

        //see if database already exists
        StockDAO.mContext = getApplicationContext();
        dbExists = StockDAO.dbExists();
        if(dbExists){
            try{
                List<Stock> list = StockDAO.readStocks();
                tree = new TreeDB();
                tree.buildTree(list);
                Log.d(DEBUG, "size: " + tree.size);
            }catch(IOException ex){
                Log.d(DEBUG, "ERROR READING FILE...");
            }
        }else{
            Log.d(DEBUG, "File does not exist");
        }


    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(DEBUG , "MainActivity.onPause()");
    }

    @Override
    public void onResume(){
        if(myMenu != null){
            if(dbExists){
                myMenu.getItem(0).setEnabled(false);
                myMenu.getItem(1).setEnabled(true);
            }else {
                myMenu.getItem(0).setEnabled(true);
                myMenu.getItem(1).setEnabled(false);
            }
        }
        Log.d(DEBUG, "MainActivity.onResume()");
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(DEBUG, "onStop()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(DEBUG, "onDestroy()");
        //Erase tree
        tree.rootStock = null;
        tree.size = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        myMenu = menu;

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        myMenu = menu;
        if(dbExists){
            myMenu.getItem(0).setEnabled(false);
            myMenu.getItem(1).setEnabled(true);
        }else {
            myMenu.getItem(0).setEnabled(true);
            myMenu.getItem(1).setEnabled(false);
        }
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
                Intent i = BuildListActivity.newIntent(MainActivity.this);
                Log.d(DEBUG, "Starting new activity");
                startActivity(i);
                Log.d(DEBUG, "New activity started");
                break;
            case(R.id.view_list):
                Log.d(DEBUG, "Viewing the list");
                //Fragment fragment = fm.findFragmentById(R.id.stock_list_fragment);
                Fragment fragment = new StockListFragment();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
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
