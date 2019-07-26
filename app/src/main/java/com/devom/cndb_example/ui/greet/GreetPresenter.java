package com.devom.cndb_example.ui.greet;

import android.app.Application;

import com.devom.cndb_example.models.Joke;

public class GreetPresenter implements GreetInteractor.OnFinishedListener {

    private GreetView view;
    private GreetInteractor interactor;

    GreetPresenter(Application app) {
        this.interactor = new GreetInteractor(app);
    }

    void setView(GreetView view) {
        this.view = view;
    }

    void onDestroy() {
        view = null;
    }


    @Override
    public void onFailure(String error) {
        if (view != null) {
            view.onFailure(error);
        }
    }

    @Override
    public void onSuccess(Joke joke) {
        if (view != null) {
            view.setTextRandom(joke);
        }
    }

    void getText() {
        interactor.getTextRandom(this);
    }

}
