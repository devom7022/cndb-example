package com.devom.cndb_example.di.component;

import com.devom.cndb_example.di.module.ApplicationContextModule;
import com.devom.cndb_example.di.module.PresenterModule;
import com.devom.cndb_example.di.module.RetrofitModule;
import com.devom.cndb_example.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    PresenterSubComponent plusPresenterSubComponent(PresenterModule presenterModule);
}
