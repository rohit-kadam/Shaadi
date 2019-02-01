package com.shaadi.base;

import android.app.Application;

import com.shaadi.di.AppComponent;
import com.shaadi.di.AppModule;
import com.shaadi.di.DaggerAppComponent;
import com.shaadi.di.RemoteModule;

/**
 * Created by Rohit on 31/01/19.
 */
public class App extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDagger();
    }

    private void initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .remoteModule(new RemoteModule()).build();
    }
}
