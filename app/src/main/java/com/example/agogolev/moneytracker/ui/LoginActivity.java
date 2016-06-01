package com.example.agogolev.moneytracker.ui;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.agogolev.moneytracker.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById
    EditText login;
    @ViewById
    EditText password;

    @Click(R.id.login_button)
    public void onLogin() {

    }

}
