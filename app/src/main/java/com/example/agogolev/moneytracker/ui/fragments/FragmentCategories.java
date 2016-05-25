package com.example.agogolev.moneytracker.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.adapters.CategoriesAdapter;
import com.example.agogolev.moneytracker.database.dbmodels.CategoriesTable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.util.ArrayList;
import java.util.List;

@OptionsMenu(R.menu.search_menu)
@EFragment(R.layout.fragment_categories)
public class FragmentCategories extends Fragment {

    private static final int ID_LOADER = 2;

    @ViewById(R.id.list_of_categori)
    RecyclerView recyclerView;
    @ViewById(R.id.fab_categori)
    FloatingActionButton fab;
    @OptionsMenuItem(R.id.search_action)
    MenuItem menuItem;
    private static final String FILTER_ID = "filter_id";

    @AfterViews
    public void initFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Message Categori", Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    @AfterViews
    public void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @AfterViews
    public void insertCategori() {
        if (CategoriesTable.getAllCategories("").isEmpty()) {
            List<String> cat = new ArrayList<>();
            cat.add("Food");
            cat.add("clothes");
            cat.add("communication");
            cat.add("For a car");
            for (String it : cat) {
                CategoriesTable categoriesTable = new CategoriesTable();
                categoriesTable.setName(it);
                categoriesTable.save();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories("");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_titel));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                BackgroundExecutor.cancelAll(FILTER_ID, true);
                queryExpenses(newText);
                return false;
            }
        });
    }

    @Background(delay = 700, id = FILTER_ID)
    public void queryExpenses(String filter) {
        loadCategories(filter);
    }

    private void loadCategories(final String filter) {

        getLoaderManager().restartLoader(ID_LOADER, null, new LoaderManager.LoaderCallbacks<List<CategoriesTable>>() {
            @Override
            public Loader<List<CategoriesTable>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<CategoriesTable>> loader = new AsyncTaskLoader<List<CategoriesTable>>(getActivity()) {
                    @Override
                    public List<CategoriesTable> loadInBackground() {
                        return CategoriesTable.getAllCategories(filter);
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<CategoriesTable>> loader, List<CategoriesTable> data) {
                CategoriesAdapter categoriesAdapter = new CategoriesAdapter(data);
                recyclerView.setAdapter(categoriesAdapter);
            }

            @Override
            public void onLoaderReset(Loader<List<CategoriesTable>> loader) {

            }
        });
    }

}
