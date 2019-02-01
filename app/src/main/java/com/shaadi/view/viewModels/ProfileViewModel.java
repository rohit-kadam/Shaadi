package com.shaadi.view.viewModels;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.shaadi.base.App;
import com.shaadi.base.BaseViewModel;
import com.shaadi.data.repository.api.ApiRepository;
import com.shaadi.models.ApiResponseModel;

import javax.inject.Inject;

/**
 * Created by Rohit on 31/01/19.
 */
public class ProfileViewModel extends BaseViewModel implements LifecycleObserver {
    @Inject ApiRepository apiRepository;
    private LiveData<ApiResponseModel> apiResponseDomainLiveData;

    public ProfileViewModel() {
        initializeDagger();
    }

    public LiveData<ApiResponseModel> requestForApi(int results) {
        apiResponseDomainLiveData = null;
        apiResponseDomainLiveData = new MutableLiveData<ApiResponseModel>();
        apiResponseDomainLiveData = apiRepository.requestForApi(results);
        return apiResponseDomainLiveData;
    }


    private void initializeDagger() {
        App.appComponent.inject(this);
    }
}
