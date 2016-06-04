package com.example.agogolev.moneytracker.rest;

import com.example.agogolev.moneytracker.rest.models.UserLoginModel;
import com.example.agogolev.moneytracker.rest.models.UserRegistrationModel;

/**
 * Created by AGogolev on 28.05.2016.
 */
public class RestService {

    private static final String REGISTER_FLAG = "1";
    private RestClient restClient;

    public RestService() {
        restClient = new RestClient();
    }

    public UserRegistrationModel register(String login, String password) {
        return restClient.getRegisterUserAPI().registerUser(login, password, REGISTER_FLAG);
    }

    public UserLoginModel login(String login, String password) {
        return restClient.getLoginUserAPI().logUser(login, password);
    }
}
