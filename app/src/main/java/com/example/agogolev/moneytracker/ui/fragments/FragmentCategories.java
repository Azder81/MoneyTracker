package com.example.agogolev.moneytracker.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.adapters.CategoriesAdapter;
import com.example.agogolev.moneytracker.database.dbmodels.CategoriesTable;
import com.example.agogolev.moneytracker.models.Categories;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_categories)
public class FragmentCategories extends Fragment {

    private static final int ID_LOADER = 2;

    @ViewById(R.id.list_of_categori)
    RecyclerView recyclerView;
    @ViewById(R.id.fab_categori)
    FloatingActionButton fab;

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
    void insertCategori() {
        if (CategoriesTable.getAllCategories().isEmpty()) {
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
        loadCategories();
    }

    private void loadCategories() {

        getLoaderManager().restartLoader(ID_LOADER, null, new LoaderManager.LoaderCallbacks<List<CategoriesTable>>() {
            @Override
            public Loader<List<CategoriesTable>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<CategoriesTable>> loader = new AsyncTaskLoader<List<CategoriesTable>>(getActivity()) {
                    @Override
                    public List<CategoriesTable> loadInBackground() {
                        return CategoriesTable.getAllCategories();
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
