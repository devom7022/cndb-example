package com.devom.cndb_example.ui.userAdd;

import android.os.Handler;

import com.devom.cndb_example.models.User;
import com.devom.cndb_example.repository.UsersSource;

import java.util.List;

public class AddUserInteractor {

    public interface OnFinishedListener {
        void onFailure(String error);

        void onSuccess(List<User> users);

    }

    public AddUserInteractor() {

    }

    void addUser(User user, OnFinishedListener listener) {
        new Handler().postDelayed(() -> {

            UsersSource.getInstance().addUser(new User(String.valueOf(UsersSource.getInstance().getUsers().size() + 1),
                    user.getName(),
                    user.getAge(),
                    user.getColor())
            );

            List<User> users = UsersSource.getInstance().getUsers();

            if (users.size() > 0)
                listener.onSuccess(users);
            else
                listener.onFailure("No hay usuarios registrados");

        }, 1000);
    }

}
