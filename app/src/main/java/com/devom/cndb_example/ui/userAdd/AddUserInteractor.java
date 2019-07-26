package com.devom.cndb_example.ui.userAdd;

import com.devom.cndb_example.models.User;
import com.devom.cndb_example.repository.UsersSource;

import java.util.List;

public class AddUserInteractor {

    public interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(List<User> users);

    }

    AddUserInteractor() {

    }

    void addUser(User user, OnFinishedListener listener) {
        user.setId(String.valueOf(UsersSource.getInstance().getUsers().size() + 1));

        UsersSource.getInstance().addUser(user);

        List<User> users = UsersSource.getInstance().getUsers();

        if (users.size() > 0)
            listener.onSuccess(users);
        else
            listener.onFailure("No hay usuarios registrados");
    }

}
