package com.example.userprofileandroidexam.presentation.viewmodel;

import com.example.userprofileandroidexam.domain.RandomUserState;
public interface UserProfileViewModelHelper {

    /**
     * Gets random user.
     */
    void getRandomUser(RandomUserState randomUserState);

    void getAllUsers();

    void clearCache();

}
