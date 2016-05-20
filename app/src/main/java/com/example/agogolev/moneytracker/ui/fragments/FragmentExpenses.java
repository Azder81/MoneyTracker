package com.example.agogolev.moneytracker.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.adapters.ExpensesAdapter;
import com.example.agogolev.moneytracker.database.dbmodels.ExpensesTable;
import com.example.agogolev.moneytracker.models.Expense;
import com.example.agogolev.moneytracker.ui.DetailActivity_;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_expenses)
public class FragmentExpenses extends Fragment {

    private static final int ID_LOADER = 1;

    @ViewById(R.id.list_of_expenses)
    RecyclerView recyclerView;
    @ViewById(R.id.fab_expens)
    FloatingActionButton fab_e;

    @AfterViews
    public void initFab() {
        fab_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity_.class);
                startActivity(intent);
            }
        });
    }

    @AfterViews
    public void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        ExpensesAdapter expencesAdapter = new ExpensesAdapter(getExpenses());
//        recyclerView.setAdapter(expencesAdapter);
    }

    @AfterViews
    public void addExpenses() {
        if (ExpensesTable.getAllExpenses().isEmpty()) {
            insertExpenses();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadExpenses();
    }

    private void loadExpenses() {

        getLoaderManager().restartLoader(ID_LOADER, null, new LoaderManager.LoaderCallbacks<List<ExpensesTable>>() {
            @Override
            public Loader<List<ExpensesTable>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<ExpensesTable>> loader = new AsyncTaskLoader<List<ExpensesTable>>(getActivity()) {
                    @Override
                    public List<ExpensesTable> loadInBackground() {
                        return ExpensesTable.getAllExpenses();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<ExpensesTable>> loader, List<ExpensesTable> data) {
                ExpensesAdapter expencesAdapter = new ExpensesAdapter(data);
                recyclerView.setAdapter(expencesAdapter);
            }

            @Override
            public void onLoaderReset(Loader<List<ExpensesTable>> loader) {

            }
        });
    }

    public void insertExpenses() {
        ExpensesTable expensesTable = new ExpensesTable();
        expensesTable.setPrice("100");
        expensesTable.setDat("01.01.2016");
        expensesTable.setDescription("минералка");
        expensesTable.save();

    }

}
