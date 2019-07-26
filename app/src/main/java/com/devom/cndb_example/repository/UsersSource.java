package com.devom.cndb_example.repository;

import com.devom.cndb_example.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersSource {
    private static UsersSource instance;

    private List<User> userList;

    private UsersSource() {
        userList = new ArrayList<>();
    }

    public static synchronized UsersSource getInstance() {
        if (instance == null) {
            instance = new UsersSource();
        }

        return instance;
    }

    public List<User> getUsers() {
        return this.userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void deleteUser(User user) {
        userList.remove(user);
    }
}
