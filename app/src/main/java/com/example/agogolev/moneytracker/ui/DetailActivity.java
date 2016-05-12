package com.example.agogolev.moneytracker.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.agogolev.moneytracker.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EActivity(R.layout.activity_detail)
public class DetailActivity extends AppCompatActivity {

    @ViewById(R.id.sum)
    EditText editSum;
    @ViewById(R.id.note)
    EditText editNote;
    @ViewById(R.id.spinner)
    Spinner spinner;
    @ViewById(R.id.date_expense)
    EditText editDate;

}
