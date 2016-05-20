package com.example.agogolev.moneytracker.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
//        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getCategori());
//        recyclerView.setAdapter(categoriesAdapter);
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

}
