package com.shaadi.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.shaadi.R;
import com.shaadi.base.BaseActivity;
import com.shaadi.models.ApiResponseModel;
import com.shaadi.view.adapters.ProfileAdapter;
import com.shaadi.view.viewModels.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;

public class MainActivity extends BaseActivity {

    private ProgressBar pbLoading;
    private RecyclerView rvProfile;

    private ProfileViewModel mProfileViewModel;
    private List<ApiResponseModel.ApiResults> mApiResultsList = new ArrayList<>();
    private ProfileAdapter mProfileAdapter;
    private final int RESULTS_COUNT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getData();
    }

    private void init() {
        mProfileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        rvProfile = findViewById(R.id.rv_profile);
        pbLoading = findViewById(R.id.pb_loading);

        mProfileAdapter = new ProfileAdapter(mApiResultsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvProfile.setLayoutManager(mLayoutManager);
        rvProfile.setItemAnimator(new OvershootInRightAnimator(1.0f));
        rvProfile.setAdapter(mProfileAdapter);
    }

    private void getData() {
        mProfileViewModel.requestForApi(RESULTS_COUNT).observe(this, new Observer<ApiResponseModel>() {
            @Override
            public void onChanged(@Nullable ApiResponseModel apiResponseModel) {
                pbLoading.setVisibility(View.GONE);
                if (apiResponseModel != null)
                    setList(apiResponseModel.getApiResults());
            }
        });
    }

    private void setList(ArrayList<ApiResponseModel.ApiResults> apiResultsList) {
        mApiResultsList.addAll(apiResultsList);
        mProfileAdapter.notifyDataSetChanged();
    }
}
