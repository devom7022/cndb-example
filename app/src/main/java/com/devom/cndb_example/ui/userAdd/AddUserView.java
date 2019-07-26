package com.devom.cndb_example.ui.userAdd;

import com.devom.cndb_example.models.User;

import java.util.List;

public interface AddUserView {
    void showProgress();

    void hideProgress();

    void onSuccessSave(List<User> users);

    void onFailure(String error);
}
