package com.example.userprofileandroidexam.data.remote;

import com.google.gson.JsonObject;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClientInterface {
    @GET("api")
    Observable<Response<JsonObject>> getUser(
            @Query("results") int id,
            @Query("page") int page
    );
}
