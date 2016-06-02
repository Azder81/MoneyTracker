package com.example.agogolev.moneytracker.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.agogolev.moneytracker.R;
import com.example.agogolev.moneytracker.rest.RestService;
import com.example.agogolev.moneytracker.rest.models.UserLoginModel;
import com.example.agogolev.moneytracker.rest.models.UserRegistrationModel;
import com.example.agogolev.moneytracker.utils.NetworkStatusChecker;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById
    EditText login;
    @ViewById
    EditText password;

    @Background
    @Click(R.id.registation_button)
    public void onRegistration(View view) {

        if (NetworkStatusChecker.isNetworkAvailable(getApplicationContext())) {
            if (inspectionEditText()) {
                RestService restService = new RestService();
                UserRegistrationModel registrationModel = restService.register(login.getText().toString(), password.getText().toString());
                if (registrationModel.getStatus().equals("success")) {
                    startMainActivity();
                } else if (registrationModel.getStatus().equals("Login busy already")) {
                    showMessage(view, "Данный логин уже занят");
                }
            } else {
                showMessage(view, "Login и password должны быть не менее 5 символов");
            }
        } else {
            showMessage(view, "Нет подключения к интернету :(");
        }
    }

    @Background
    @Click(R.id.login_button)
    public void onLogin(View view) {
        if (NetworkStatusChecker.isNetworkAvailable(getApplicationContext())) {
            if (inspectionEditText()) {
                RestService restService = new RestService();
                UserLoginModel userLoginModel = restService.login(login.getText().toString(), password.getText().toString());
                if (userLoginModel.getStatus().equals("success")) {
                    startMainActivity();
                } else if (userLoginModel.getStatus().equals("Wrong login")) {
                    showMessage(view, "Неверный Login");
                } else if (userLoginModel.getStatus().equals("Wrong password")) {
                    showMessage(view, "Неверный пароль");
                } else if (userLoginModel.getStatus().equals("Error")) {
                    showMessage(view, "ОШИБКА!!!");
                }
            } else {
                showMessage(view, "Login и password должны быть не менее 5 символов");
            }
        } else {
            showMessage(view, "Нет подключения к интернету :(");
        }

    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
        startActivity(intent);
    }

    private boolean inspectionEditText() {
        if (login.getText().toString().length() < 5 || password.getText().length() < 5)
            return false;
        else
            return true;
    }

    private void showMessage(View view, String mes) {
        Snackbar.make(view, mes, Snackbar.LENGTH_SHORT).show();
    }
}
