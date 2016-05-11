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
import com.example.agogolev.moneytracker.models.Categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AGogolev on 21.04.2016.
 */
public class FragmentCategories extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_categories, container,false);
        initRecycleView(rootView);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab_categori);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Message Categori", Snackbar.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }

    private void initRecycleView(View rootView){
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_categori);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getCategori());
        recyclerView.setAdapter(categoriesAdapter);
    }

    private List<Categories> getCategori(){
        List<Categories> cat = new ArrayList<>();
        cat.add(new Categories("Food"));
        cat.add(new Categories("clothes"));
        cat.add(new Categories("communication"));
        cat.add(new Categories("For a car"));
        return cat;
    }

}
