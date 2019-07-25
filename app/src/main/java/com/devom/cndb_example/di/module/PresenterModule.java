package com.devom.cndb_example.di.module;

import com.devom.cndb_example.api.ApiClient;
import com.devom.cndb_example.di.scope.SessionScope;
import com.devom.cndb_example.ui.user.UserInteractor;
import com.devom.cndb_example.ui.user.UserPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @SessionScope
    UserInteractor providerCitizenInteractor(ApiClient apiClient) {
        return new UserInteractor(apiClient);
    }

    @Provides
    @SessionScope
    UserPresenter providesPresenterCitizen(UserInteractor interactor) {
        return new UserPresenter(interactor);
    }
}
