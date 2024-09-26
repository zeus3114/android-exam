package com.example.userprofileandroidexam.data.repository.helper;

import com.example.userprofileandroidexam.data.model.response.UserResponse;

import io.reactivex.Observable;

public interface UserInfoDataRepository {
  /**
  *  Gets Random users
  * */
  Observable<UserResponse> getRandomUser(final int PAGE_INDEX);

}
