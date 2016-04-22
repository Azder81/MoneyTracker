package com.example.agogolev.moneytracker;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        setupDrawerLayout();
        if (savedInstanceState == null){
            replaceFragment(new FragmentExpenses());
        }
        Log.d(LOG_TAG, "***-*** onCreate");
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.app_name));
    }

    private void setTitelFragments(String fragment_name){
        if (fragment_name.contains("FragmentExpenses")){
            setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_expenses));
        }else if (fragment_name.contains("FragmentCategories")){
            setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_categories));
        }else if (fragment_name.contains("FragmentSettings")){
            setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_settings));
        }else if (fragment_name.contains("FragmentStatistic")){
            setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_statistic));
        }
//        switch (id_item){
//            case R.id.drawer_expenses:
//                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_expenses));
//                break;
//            case R.id.drawer_categories:
//                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_categories));
//                break;
//            case R.id.drawer_settings:
//                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_settings));
//                break;
//            case R.id.drawer_statistics:
//                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_statistic));
//                break;
//        }
    }

    private void replaceFragment(Fragment fragment){

        String backStackName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStackName,0);

        if (!fragmentPopped && manager.findFragmentByTag(backStackName) == null){
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.main_container, fragment, backStackName);
            ft.addToBackStack(backStackName);
            ft.commit();
            setTitelFragments(backStackName);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        switch (item.getItemId()){
            case R.id.drawer_expenses:
                FragmentExpenses ef = new FragmentExpenses();
                replaceFragment(ef);
                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_expenses));
                break;
            case R.id.drawer_categories:
                FragmentCategories cf = new FragmentCategories();
                replaceFragment(cf);
                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_categories));
                break;
            case R.id.drawer_settings:
                FragmentSettings sf = new FragmentSettings();
                replaceFragment(sf);
                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_settings));
                break;
            case R.id.drawer_statistics:
                FragmentStatistic stf = new FragmentStatistic();
                replaceFragment(stf);
                setTitle(getString(R.string.app_name)+" :"+getString(R.string.fragm_statistic));
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (manager.getBackStackEntryCount() == 1) {
            supportFinishAfterTransition();
        } else{
            super.onBackPressed();
            String fragmentTag = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            setTitelFragments(fragmentTag);
        }

    }
}
