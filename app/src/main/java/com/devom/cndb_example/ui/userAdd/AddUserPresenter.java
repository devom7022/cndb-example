package com.devom.cndb_example.ui.userAdd;

import com.devom.cndb_example.models.User;

import java.util.List;

public class AddUserPresenter implements AddUserInteractor.OnFinishedListener {

    private AddUserView view;
    private AddUserInteractor interactor;

    /*public AddUserPresenter(AddUserInteractor interactor) {
        this.interactor = interactor;
    }*/

    public AddUserPresenter() {
        this.interactor = new AddUserInteractor();
    }

    void setView(AddUserView view) {
        this.view = view;
    }

    void onDestroy() {
        view = null;
    }

    void setUserToList(User user) {
        interactor.addUser(user, this);
    }

    @Override
    public void onFailure(String error) {
        if (view != null) {
            view.onFailure(error);
        }
    }

    @Override
    public void onSuccess(List<User> userList) {
        if (view != null) {
            view.onSuccessSave(userList);
        }
    }
}
