package com.devom.cndb_example.ui.greet;

import com.devom.cndb_example.models.Joke;

public class GreetPresenter implements GreetInteractor.OnFinishedListener {

    private GreetView view;
    private GreetInteractor interactor;

    public GreetPresenter(GreetInteractor interactor) {
        this.interactor = interactor;
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
