package com.example.agogolev.moneytracker.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.agogolev.moneytracker.ui.fragments.FragmentCategories_;
import com.example.agogolev.moneytracker.ui.fragments.FragmentExpenses_;
import com.example.agogolev.moneytracker.ui.fragments.FragmentSettings_;
import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.ui.fragments.FragmentStatistic_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @ViewById
    Toolbar toolbar;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @ViewById(R.id.navigation_view)
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////        setActionBar();
////        setupDrawerLayout();
//        if (savedInstanceState == null) {
//            replaceFragment(new FragmentExpenses());
//        }
//    }

    @AfterViews
    void setInitFragment() {
        replaceFragment(new FragmentExpenses_());
    }

    @AfterViews
    void setActionBar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @AfterViews
    void setupDrawerLayout() {
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.app_name));
    }

    private void setMenuActive(String fragment_name) {
        int nItem = R.id.drawer_expenses;
        if (fragment_name.contains("FragmentExpenses_")) {
            nItem = R.id.drawer_expenses;
        } else if (fragment_name.contains("FragmentCategories_")) {
            nItem = R.id.drawer_categories;
        } else if (fragment_name.contains("FragmentSettings")) {
            nItem = R.id.drawer_settings;
        } else if (fragment_name.contains("FragmentStatistic")) {
            nItem = R.id.drawer_statistics;
        }
        navigationView.setCheckedItem(nItem);
    }

    private void replaceFragment(Fragment fragment) {
        String backStackName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStackName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStackName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.main_container, fragment, backStackName);
            ft.addToBackStack(backStackName);
            ft.commit();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        switch (item.getItemId()) {
            case R.id.drawer_expenses:
                FragmentExpenses_ ef = new FragmentExpenses_();
                replaceFragment(ef);
                break;
            case R.id.drawer_categories:
                FragmentCategories_ cf = new FragmentCategories_();
                replaceFragment(cf);
                break;
            case R.id.drawer_settings:
                FragmentSettings_ sf = new FragmentSettings_();
                replaceFragment(sf);
                break;
            case R.id.drawer_statistics:
                FragmentStatistic_ stf = new FragmentStatistic_();
                replaceFragment(stf);
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
        } else {
            super.onBackPressed();
            String fragmentTag = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            setMenuActive(fragmentTag);
        }
    }

}
