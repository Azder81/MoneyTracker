package com.example.agogolev.moneytracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AGogolev on 21.04.2016.
 */
public class ExpencesAdapter extends RecyclerView.Adapter<ExpencesAdapter.ExpencesHolder> {

    private List<Expence> expenseList;

    public ExpencesAdapter(List<Expence> expenceList){
        this.expenseList = expenceList;
    }

    @Override
    public ExpencesAdapter.ExpencesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent,false);
        return new ExpencesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpencesAdapter.ExpencesHolder holder, int position) {
        Expence expence = expenseList.get(position);
        holder.description.setText(expence.description);
        holder.price.setText(expence.price);
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
