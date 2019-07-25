package com.devom.cndb_example.ui.user;

import com.devom.cndb_example.models.User;

import java.util.List;

public interface UserView {
    void showProgress();

    void hideProgress();

    void setItemsOnAdapters(List<User> users);

    void onFailure(String error);
}
