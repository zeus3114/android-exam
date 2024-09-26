package com.example.userprofileandroidexam.data.repository;

import static com.example.userprofileandroidexam.helper.Constants.PAGE_INDEX;
import static com.example.userprofileandroidexam.helper.Constants.USER_INDEX;

import com.example.userprofileandroidexam.data.local.AppDatabase;
import com.example.userprofileandroidexam.data.model.response.UserResponse;
import com.example.userprofileandroidexam.data.remote.ApiClientInterface;
import com.example.userprofileandroidexam.data.repository.helper.UserInfoDataRepository;
import com.example.userprofileandroidexam.helper.JsonConverter;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;


public class UserProfileDataRepository implements UserInfoDataRepository {
    private final ApiClientInterface apiClientInterface;
    private AppDatabase appDatabase;

    @Inject
    public UserProfileDataRepository(ApiClientInterface apiClientInterface) {
        this.apiClientInterface = apiClientInterface;
    }

    @Override
    public Observable<UserResponse> getRandomUser(final int pageIndex) {
        Observable<Response<JsonObject>> request = apiClientInterface.getUser(USER_INDEX, PAGE_INDEX);
        return request.map(jsonObjectResponse -> JsonConverter.convertToJsonToUser(jsonObjectResponse));
    }
}
