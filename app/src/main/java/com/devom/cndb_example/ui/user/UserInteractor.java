package com.devom.cndb_example.ui.user;

import android.os.Handler;

import com.devom.cndb_example.api.ApiClient;
import com.devom.cndb_example.models.User;
import com.devom.cndb_example.repository.UsersSource;

import java.util.List;

public class UserInteractor {
    private ApiClient apiClient;
    private List<User> dataResponse;

    interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(List<User> responseCity);
    }

    public UserInteractor(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    void addUser(User user, OnFinishedListener listener) {

    }

    void getUsers(final OnFinishedListener listener) {
        new Handler().postDelayed(() -> {
            UsersSource dataSource = UsersSource.getInstance();
            List<User> users = dataSource.getUsers();
            if (users.size()>0)
                listener.onSuccess(users);
            else
                listener.onFailure("No hay usuarios registrados");

        }, 2000);
    }

}
