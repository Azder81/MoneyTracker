package com.example.agogolev.moneytracker.rest;

import com.example.agogolev.moneytracker.rest.models.UserLoginModel;

import retrofit.http.GET;
import retrofit.http.Query;

public interface LoginUserAPI {

    @GET("/auth")
    UserLoginModel logUser(@Query("login") String login,
                           @Query("password") String password);
}
