package com.example.agogolev.moneytracker.ui;


import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.database.dbmodels.CategoriesTable;
import com.example.agogolev.moneytracker.database.dbmodels.ExpensesTable;
import com.example.agogolev.moneytracker.utils.DatePickerFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EActivity(R.layout.activity_detail)
public class DetailActivity extends AppCompatActivity {

    private Map<String, String> hm;
    private ArrayList<String> dataSpiner = new ArrayList<String>();
    private long idM;
    private ArrayAdapter<String> adapter;
    private static final int ID_LOADER = 3;

    @ViewById
    EditText sum;
    @ViewById
    EditText note;
    @ViewById
    Spinner spinner;
    @ViewById(R.id.date_expense)
    TextView editDate;

    @AfterViews
    public void createSpiner() {

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dataSpiner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Категории");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(12);
//                idM = Long.parseLong(hm.get(dataSpiner[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateSpiner() {
        adapter.notifyDataSetChanged();
    }

    @AfterViews
    public void createDateText() {
        Calendar cal = Calendar.getInstance();
        String yy = Integer.toString(cal.get(Calendar.YEAR));
        String mm = cal.get(Calendar.MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.MONTH) + 1) : Integer.toString(cal.get(Calendar.MONTH) + 1);
        String dd = cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) : Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        editDate.setText(new StringBuilder()
                .append(dd).append(".")
                .append(mm).append(".")
                .append(yy)
        );
    }

    @Click(R.id.button)
    public void onAdd(View view) {
        ExpensesTable expensesTable = new ExpensesTable();
        expensesTable.setPrice(sum.getText().toString());
        expensesTable.setDescription(note.getText().toString());
        expensesTable.setDat(editDate.getText().toString());

        idM = Long.parseLong(hm.get(spinner.getSelectedItem()));
        CategoriesTable categoriesTable = CategoriesTable.getById(idM);
        expensesTable.associateCategori(categoriesTable);
        expensesTable.save();
        finish();
//        Snackbar.make(view, "Добавили Сумма: " + sum.getText() + "; Категория: " + categori, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategories();
    }

    private void loadCategories() {

        getLoaderManager().restartLoader(ID_LOADER, null, new LoaderManager.LoaderCallbacks<List<CategoriesTable>>() {
            @Override
            public Loader<List<CategoriesTable>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<CategoriesTable>> loader = new AsyncTaskLoader<List<CategoriesTable>>(DetailActivity.this) {
                    @Override
                    public List<CategoriesTable> loadInBackground() {
                        return CategoriesTable.getAllCategories();
                    }

                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(android.content.Loader<List<CategoriesTable>> loader, List<CategoriesTable> data) {
                //для того что бы получить потом обратно id категории, положим все в HashMap
                //с ключен name и значениями id, тогда можно найти id по name :)
                hm = new HashMap<String, String>();
                for (int i = 0; i < data.size(); i++) {
                    hm.put(data.get(i).getName(), Long.toString(data.get(i).getId()));
                    dataSpiner.add(data.get(i).getName());
                }
                updateSpiner();
            }

            @Override
            public void onLoaderReset(android.content.Loader<List<CategoriesTable>> loader) {

            }
        });
    }

    public void onClickDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
