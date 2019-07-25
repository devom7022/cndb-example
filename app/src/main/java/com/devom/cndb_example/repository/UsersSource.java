package com.devom.cndb_example.repository;

import android.util.Log;

import com.devom.cndb_example.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersSource {
    private static String TAG = UsersSource.class.getSimpleName();
    private static UsersSource instance;

    private List<User> userList = new ArrayList<>();

    private UsersSource() {
    }

    public static UsersSource getInstance() {
        if (instance == null) {
            instance = new UsersSource();
        }

        return instance;
    }

    public void showTAG() {
        Log.d(TAG, "dataSource");
    }

    public List<User> getUsers() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
