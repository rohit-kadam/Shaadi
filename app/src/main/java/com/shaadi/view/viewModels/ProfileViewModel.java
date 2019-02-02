package com.shaadi.view.viewModels;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;

import com.shaadi.base.App;
import com.shaadi.base.BaseViewModel;
import com.shaadi.data.repository.api.ApiRepository;
import com.shaadi.models.ApiResponseModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Rohit on 31/01/19.
 */
public class ProfileViewModel extends BaseViewModel implements LifecycleObserver {

    @Inject ApiRepository apiRepository;
    private LiveData<ApiResponseModel> apiResponseDomainLiveData;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProfileViewModel() {
        initializeDagger();
    }

    public LiveData<ApiResponseModel> requestForApi(int results) {
        apiResponseDomainLiveData = null;
        apiResponseDomainLiveData = new MutableLiveData<ApiResponseModel>();
        apiResponseDomainLiveData = apiRepository.requestForApi(results);
        return apiResponseDomainLiveData;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void unSubscribeViewModel() {

        for (Disposable disposable : apiRepository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable);
        }

        compositeDisposable.clear();
    }

    @Override
    protected void onCleared() {
        unSubscribeViewModel();
        super.onCleared();
    }

    private void initializeDagger() {
        App.appComponent.inject(this);
    }
}
