package com.shaadi.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shaadi.data.remote.api.RemoteApiService;
import com.shaadi.data.remote.base.RemoteContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rohit on 31/01/19.
 */
@Module
public class RemoteModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(RemoteContract.BASE_API_LAYER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public RemoteApiService provideRemoteProfileService(Retrofit retrofit) {
        return retrofit.create(RemoteApiService.class);
    }
}
