package com.example.agogolev.moneytracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agogolev.moneytracker.database.dbmodels.CategoriesTable;
import com.example.agogolev.moneytracker.models.Categories;
import com.example.agogolev.moneytracker.R;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesHolden> {

    private List<CategoriesTable> categoriesList;

    public CategoriesAdapter(List<CategoriesTable> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public CategoriesHolden onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, parent, false);
        return new CategoriesHolden(itemView);
    }

    @Override
    public void onBindViewHolder(CategoriesHolden holder, int position) {
        CategoriesTable categories = categoriesList.get(position);
        holder.categories_name.setText(categories.getName());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class CategoriesHolden extends RecyclerView.ViewHolder{

        public TextView categories_name;

        public CategoriesHolden(View itemView) {
            super(itemView);
            categories_name = (TextView) itemView.findViewById(R.id.categories_name);
        }
    }
}
