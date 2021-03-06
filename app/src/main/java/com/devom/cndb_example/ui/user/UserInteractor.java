package com.devom.cndb_example.ui.user;

import android.service.autofill.UserData;

import com.devom.cndb_example.models.User;
import com.devom.cndb_example.repository.UsersSource;

import java.util.List;

public class UserInteractor {

    private List<User> users;
    //private UsersSource dataSource;

    public interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(List<User> responseCity);
    }

    public UserInteractor() {
        //dataSource = UsersSource.getInstance();
    }

    void deleteUser(User user, OnFinishedListener listener) {
        try {
            UsersSource.getInstance().deleteUser(user);
            //dataSource.deleteUser(user);
            users = UsersSource.getInstance().getUsers();
            listener.onSuccess(users);
        } catch (Exception e) {
            listener.onFailure("No se pudo borrar el usuario");
        }
    }

    void getUsers(OnFinishedListener listener) {
        users = UsersSource.getInstance().getUsers();
        if (users.size() > 0)
            listener.onSuccess(users);
        else
            listener.onFailure("No hay usuarios registrados");
    }
}
