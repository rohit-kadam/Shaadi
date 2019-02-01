package com.shaadi.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaadi.R;
import com.shaadi.models.ApiResponseModel;

import java.util.List;

/**
 * Created by Rohit on 01/02/19.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<ApiResponseModel.ApiResults> apiResults;

    class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLastSeen, tvName, tvAgeState, tvCityDegree, tvJobTitle;
        private ImageView ivProfile, ivDecline, ivConnect;

        private ProfileViewHolder(View view) {
            super(view);
            tvLastSeen = view.findViewById(R.id.tv_last_seen);
            tvName = view.findViewById(R.id.tv_name);
            tvAgeState = view.findViewById(R.id.tv_age_state);
            tvCityDegree = view.findViewById(R.id.tv_city_degree);
            tvJobTitle = view.findViewById(R.id.tv_job_title);

            ivProfile = view.findViewById(R.id.iv_profile);
            ivDecline = view.findViewById(R.id.iv_decline);
            ivConnect = view.findViewById(R.id.iv_connect);
        }

        void bind(ApiResponseModel.ApiResults apiResult) {
            Glide.with(ivProfile.getContext())
                .load(apiResult.getPicture().getMedium())
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile);

            ApiResponseModel.UserName userName = apiResult.getName();
            tvName.setText(String.format("%s %s %s", userName.getTitle(), userName.getFirst(), userName.getLast()));

            ApiResponseModel.Dob dob = apiResult.getDob();
            ApiResponseModel.Location location = apiResult.getLocation();
            tvAgeState.setText(String.format("%s %s %s", dob.getAge(), location.getState(), location.getStreet()));

            //degree is missing in the api
            tvCityDegree.setText(String.format("%s", location.getCity()));

            //job title is missing in the api. that's why hardcoded
            tvJobTitle.setText(String.format("%s", tvJobTitle.getContext().getString(R.string.business_owner_entrepreneur)));

            ivDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(getAdapterPosition());
                }
            });

            ivConnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ivConnect.getContext(), "Connect clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public ProfileAdapter(List<ApiResponseModel.ApiResults> apiResults) {
        this.apiResults = apiResults;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        ApiResponseModel.ApiResults apiResult = apiResults.get(position);

        holder.bind(apiResult);
    }

    @Override
    public int getItemCount() {
        return apiResults.size();
    }

    public void remove(int position) {
        apiResults.remove(position);
        notifyItemRemoved(position);
    }
}