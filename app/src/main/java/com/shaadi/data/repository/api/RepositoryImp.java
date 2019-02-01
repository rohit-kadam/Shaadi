package com.shaadi.data.repository.api;

import android.arch.lifecycle.LiveData;
import com.shaadi.models.ApiResponseModel;

/**
 * Created by Rohit on 31/01/19.
 */
interface RepositoryImp {
    LiveData<ApiResponseModel> requestForApi(int results);
}
