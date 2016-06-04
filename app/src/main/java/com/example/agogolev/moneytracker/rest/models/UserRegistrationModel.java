package com.example.agogolev.moneytracker.rest.models;

import com.google.gson.annotations.SerializedName;

public class UserRegistrationModel {

    @SerializedName("status")
    private String status;
    @SerializedName("id")
    private Integer id;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

}