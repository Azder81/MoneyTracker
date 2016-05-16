package com.example.agogolev.moneytracker.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.utils.DatePickerFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

@EActivity(R.layout.activity_detail)
public class DetailActivity extends AppCompatActivity {

    private String categori;

    @ViewById
    EditText sum;
    @ViewById
    EditText note;
    @ViewById
    Spinner spinner;
    @ViewById(R.id.date_expense)
    TextView editDate;

    String[] data = {"Food", "clothes", "communication", "For a car"};

    @AfterViews
    public void createSpiner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Категории");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categori = data[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @AfterViews
    public void createDateText() {
        Calendar cal = Calendar.getInstance();
        String yy = Integer.toString(cal.get(Calendar.YEAR));
        String mm = cal.get(Calendar.MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.MONTH) + 1) : Integer.toString(cal.get(Calendar.MONTH) + 1);
        ;
        String dd = cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) : Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        editDate.setText(new StringBuilder()
                .append(dd).append(".")
                .append(mm).append(".")
                .append(yy)
        );
    }

    @Click(R.id.button)
    public void onAdd(View view) {
        Snackbar.make(view, "Добавили Сумма: " + sum.getText() + "; Категория: " + categori, Snackbar.LENGTH_SHORT).show();
//        this.finish();
    }

    public void onClickDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
