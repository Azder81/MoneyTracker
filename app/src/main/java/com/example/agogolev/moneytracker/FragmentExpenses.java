package com.example.agogolev.moneytracker;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AGogolev on 21.04.2016.
 */
public class FragmentExpenses extends Fragment{


    private RecyclerView recyclerView;
    private FloatingActionButton fab_e;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expenses,container, false);
        initRecycleView(rootView);
        fab_e = (FloatingActionButton) rootView.findViewById(R.id.fab_expens);
        fab_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Message Expenses", Snackbar.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    private void initRecycleView(View rootView){
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_expenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExpensesAdapter expencesAdapter = new ExpensesAdapter(getExpenses());
        recyclerView.setAdapter(expencesAdapter);
    }

    private List<Expense> getExpenses(){
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
