package com.devom.cndb_example.di.module;

import com.devom.cndb_example.di.scope.SessionScope;
import com.devom.cndb_example.ui.user.UserInteractor;
import com.devom.cndb_example.ui.user.UserPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    /*@Provides
    @SessionScope
    AddUserInteractor providerAddInteractor() {
        return new AddUserInteractor();
    }

    @Provides
    @SessionScope
    AddUserPresenter providesAddPresenter(AddUserInteractor interactor) {
        return new AddUserPresenter(interactor);
    }*/

    @Provides
    @SessionScope
    UserInteractor providerUserInteractor() {
        return new UserInteractor();
    }

    @Provides
    @SessionScope
    UserPresenter providesUserPresenter(UserInteractor interactor) {
        return new UserPresenter(interactor);
    }
/*
    @Provides
    @SessionScope
    GreetInteractor providerGreetInteractor(ApiClient apiClient) {
        return new GreetInteractor(apiClient);
    }

    @Provides
    @SessionScope
    GreetPresenter providesGreetPresenter(GreetInteractor interactor) {
        return new GreetPresenter(interactor);
    }
*/
}
