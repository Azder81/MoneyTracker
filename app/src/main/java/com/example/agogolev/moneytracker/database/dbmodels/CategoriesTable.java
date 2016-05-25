package com.example.agogolev.moneytracker.database.dbmodels;

import com.example.agogolev.moneytracker.database.MoneyTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@ModelContainer
@Table(database = MoneyTrackerDatabase.class)
public class CategoriesTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String name;

    List<ExpensesTable> expensesTable;

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "expensesTable")
    public List<ExpensesTable> getExpensesTable() {

        if (expensesTable == null || expensesTable.isEmpty()) {
            expensesTable = SQLite.select()
                    .from(ExpensesTable.class)
                    .where(ExpensesTable_Table.categoriesTable_id.eq(id))
                    .queryList();
        }

        return expensesTable;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<CategoriesTable> getAllCategories(String filter) {
        return SQLite.select()
                .from(CategoriesTable.class)
                .where(CategoriesTable_Table.name.like("%" + filter + "%"))
                .queryList();
    }

    public static CategoriesTable getById(long idM) {
        return SQLite.select()
                .from(CategoriesTable.class)
                .where(CategoriesTable_Table.id.eq(idM))
                .querySingle();
    }
}
