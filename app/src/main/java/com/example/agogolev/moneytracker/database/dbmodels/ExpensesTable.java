package com.example.agogolev.moneytracker.database.dbmodels;

import com.example.agogolev.moneytracker.database.MoneyTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.List;

@ModelContainer
@Table(database = MoneyTrackerDatabase.class)
public class ExpensesTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String price;

    @Column
    String description;

    @Column
    String dat;

    @ForeignKey
    ForeignKeyContainer<CategoriesTable> categoriesTable;

    public void associateCategori(CategoriesTable catTable) {

        this.categoriesTable = FlowManager.getContainerAdapter(CategoriesTable.class)
                .toForeignKeyContainer(catTable);
    }

    public long getId() {
        return id;
    }

//    public void setCategoriesTable(ForeignKeyContainer<CategoriesTable> categoriesTable) {
//        this.categoriesTable = categoriesTable;
//    }

    public String getDat() {
        return dat;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    @Override
    public ModelAdapter getModelAdapter() {
        return super.getModelAdapter();
    }



    public static List<ExpensesTable> getAllExpenses() {
        return SQLite.select()
                .from(ExpensesTable.class)
                .queryList();
    }
}
