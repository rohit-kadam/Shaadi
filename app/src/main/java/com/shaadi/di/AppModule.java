package com.shaadi.di;

import android.content.Context;

import com.shaadi.base.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rohit on 31/01/19.
 */
@Module
public class AppModule {

    private App appApplication;

    public AppModule(App appApplication) {
        this.appApplication = appApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return appApplication;
    }
}
