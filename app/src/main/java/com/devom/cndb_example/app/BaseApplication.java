package com.devom.cndb_example.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.devom.cndb_example.di.component.ApplicationComponent;
import com.devom.cndb_example.di.component.DaggerApplicationComponent;
import com.devom.cndb_example.di.module.ApplicationContextModule;


public class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class.getSimpleName();

    private static BaseApplication mInstance;
    private static ConnectivityManager connManager;
    private static NetworkInfo mWifi;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setupGraphApplicationComponent();
    }

    //Request Instance
    public static BaseApplication getInstance() {
        return mInstance;
    }

    //Connection to Network
    public static boolean getConnectionToNetwork() {
        connManager = (ConnectivityManager) mInstance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    //Application
    private void setupGraphApplicationComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
