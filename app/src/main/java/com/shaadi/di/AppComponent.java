package com.shaadi.di;

import android.content.Context;

import com.shaadi.base.BaseViewModel;
import com.shaadi.view.viewModels.ProfileViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Rohit on 31/01/19.
 */
@Singleton
@Component(modules = {AppModule.class, RemoteModule.class})
public interface AppComponent {
    void inject(BaseViewModel baseViewModel);
    void inject(ProfileViewModel baseViewModel);
    Context getContext();
}
