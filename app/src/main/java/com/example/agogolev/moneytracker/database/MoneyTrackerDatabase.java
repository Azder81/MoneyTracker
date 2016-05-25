package com.example.agogolev.moneytracker.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MoneyTrackerDatabase.NAME, version = MoneyTrackerDatabase.VERSION)
public class MoneyTrackerDatabase {

    public static final String NAME = "moneytrackerdb";
    public static final int VERSION = 1;

}
