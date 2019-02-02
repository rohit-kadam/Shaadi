package com.shaadi.data.repository.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.shaadi.models.ApiResponseModel;
import com.shaadi.data.remote.api.RemoteApiDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rohit on 31/01/19.
 */
public class ApiRepository implements RepositoryImp {

    private RemoteApiDataSource remoteApiDataSource;
    public List<Disposable> allCompositeDisposable = new ArrayList<>();

    @Inject
    public ApiRepository(RemoteApiDataSource remoteApiDataSource) {
        this.remoteApiDataSource = remoteApiDataSource;
    }

    @Override
    public LiveData<ApiResponseModel> requestForApi(int results) {
        final MutableLiveData<ApiResponseModel> mutableApiLiveData = new MutableLiveData<>();

        Disposable disposable = remoteApiDataSource
                .requestForApi(results)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponseModel>() {
                    @Override
                    public void accept(ApiResponseModel apiResponse) throws Exception {
                        mutableApiLiveData.setValue(apiResponse);
                        //mutableApiLiveData.setValue(transform(apiResponse));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mutableApiLiveData.setValue(null);
                    }
                });

        allCompositeDisposable.add(disposable);

        return mutableApiLiveData;
    }
}
