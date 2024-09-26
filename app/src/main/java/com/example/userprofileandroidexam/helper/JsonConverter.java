package com.example.userprofileandroidexam.helper;

import com.example.userprofileandroidexam.data.model.response.User;
import com.example.userprofileandroidexam.data.model.response.UserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class JsonConverter {
    private static final Gson gson = new Gson();

    public static UserResponse convertToJsonToUser(Response<JsonObject> jsonObjectResponse) throws JSONException, IOException {

        List<User> userList = new ArrayList<>();
        User userInfo = new User();

        JSONObject jsonObject = new JSONObject(jsonObjectResponse.body().toString());
        String jason = String.valueOf(jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        UserResponse page = gson.fromJson(jsonObjectResponse.body(), UserResponse.class);

        for (int i = 0; i < jsonArray.length(); i++) {
            userInfo = gson.fromJson(jsonArray.get(i).toString(), User.class);
            userList.add(new User(
                    userInfo.getUserName().getTitle(),
                    userInfo.getUserName().getFirstName(),
                    userInfo.getUserName().getLastName(),
                    userInfo.getEmail(),
                    userInfo.getPhone(),
                    userInfo.getDob().getBirthday(),
                    userInfo.getUserAddress().getCity(),
                    userInfo.getUserAddress().getStreet().getStreetName(),
                    userInfo.getUserAddress().getStreet().getStreetNumber(),
                    userInfo.getUserAddress().getState(),
                    userInfo.getUserPicture().getLarge(),
                    userInfo.getUserPicture().getMedium(),
                    userInfo.getUserPicture().getThumbnail()
            ));
        }

        return new UserResponse(userList, page.getPageInfo());
    }
}
