package com.example.agogolev.moneytracker.ui.fragments;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.adapters.ExpensesAdapter;
import com.example.agogolev.moneytracker.models.Expense;
import com.example.agogolev.moneytracker.ui.DetailActivity_;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_expenses)
public class FragmentExpenses extends Fragment {


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
        ExpensesAdapter expencesAdapter = new ExpensesAdapter(getExpenses());
        recyclerView.setAdapter(expencesAdapter);
    }

    private List<Expense> getExpenses() {
        List<Expense> expences = new ArrayList<>();
        expences.add(new Expense("Cinema", "120"));
        expences.add(new Expense("Cafe", "150"));
        expences.add(new Expense("Car", "1200000"));
        expences.add(new Expense("cake", "15"));
        expences.add(new Expense("egg", "56"));
        expences.add(new Expense("Tea", "65"));
        expences.add(new Expense("Coffee Pele", "135"));
        expences.add(new Expense("bread", "21"));
        return expences;
    }

}
