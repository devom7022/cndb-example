package com.devom.cndb_example.di.component;

import com.devom.cndb_example.di.module.PresenterModule;
import com.devom.cndb_example.di.scope.SessionScope;
import com.devom.cndb_example.ui.greet.GreetActivity;
import com.devom.cndb_example.ui.user.UserActivity;
import com.devom.cndb_example.ui.userAdd.AddUserActivity;

import dagger.Subcomponent;

@SessionScope
@Subcomponent(modules = {PresenterModule.class})
public interface PresenterSubComponent {
    void inject(AddUserActivity addUserActivity);

    void inject(GreetActivity greetActivity);

    void inject(UserActivity activity);
}
