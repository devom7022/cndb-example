package com.devom.cndb_example.ui.user;

import com.devom.cndb_example.models.User;

import java.util.List;

public class UserPresenter implements UserInteractor.OnFinishedListener {

    private UserView view;
    private UserInteractor interactor;

    UserPresenter() {
        this.interactor = new UserInteractor();
    }

    void setView(UserView view) {
        this.view = view;
    }

    void onDestroy() {
        view = null;
    }

    public void deleteUser(User user){
        interactor.deleteUser(user,this);
    }

    void getUserList() {
        if (view != null) {
            view.showProgress();
        }
        interactor.getUsers(this);
    }

    @Override
    public void onFailure(String error) {
        if (view != null) {
            view.hideProgress();
            view.onFailure(error);
        }
    }

    @Override
    public void onSuccess(List<User> users) {
        if (view != null) {
            view.setItemsOnAdapters(users);
            view.hideProgress();
        }
    }
}
