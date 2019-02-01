package com.shaadi.data.remote.api;

import com.shaadi.models.ApiResponseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Rohit on 31/01/19.
 */
public class RemoteApiDataSource {

    private RemoteApiService remoteApiService;

    @Inject
    public RemoteApiDataSource(RemoteApiService remoteApiService) {
        this.remoteApiService = remoteApiService;
    }

    public Observable<ApiResponseModel> requestForApi(int results) {
        return remoteApiService.requestForApi(results);
    }
}
