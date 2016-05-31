package com.example.agogolev.moneytracker.rest;


import retrofit.RestAdapter;

public class RestClient {

    private static final String BASE_URL = "http://lmt.loftblog.tmweb.ru";
    private RegisterUserAPI registerUserAPI;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        registerUserAPI = restAdapter.create(RegisterUserAPI.class);
    }

    public RegisterUserAPI getRegisterUserAPI() {
        return registerUserAPI;
    }
}

