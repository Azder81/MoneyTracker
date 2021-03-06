package com.example.agogolev.moneytracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agogolev.moneytracker.database.dbmodels.ExpensesTable;
import com.example.agogolev.moneytracker.models.Expense;
import com.example.agogolev.moneytracker.R;

import java.util.List;

/**
 * Created by AGogolev on 21.04.2016.
 */
public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpencesHolder> {

    private List<ExpensesTable> expenseList;

    public ExpensesAdapter(List<ExpensesTable> expenceList) {
        this.expenseList = expenceList;
    }

    @Override
    public ExpensesAdapter.ExpencesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent,false);
        return new ExpencesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpensesAdapter.ExpencesHolder holder, int position) {
        ExpensesTable expence = expenseList.get(position);
        holder.description.setText(expence.getDescription());
        holder.price.setText(expence.getPrice());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    class ExpencesHolder extends RecyclerView.ViewHolder {

        public TextView description;
        public TextView price;

        public ExpencesHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
