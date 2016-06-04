package com.example.agogolev.moneytracker.rest;

import com.example.agogolev.moneytracker.rest.models.UserRegistrationModel;

import retrofit.http.GET;
import retrofit.http.Query;

public interface RegisterUserAPI {

    @GET("/auth")
    UserRegistrationModel registerUser(@Query("login") String login,
                                       @Query("password") String password,
                                       @Query("register") String register);
}
