package com.example.userprofileandroidexam.di.builder;

import com.example.userprofileandroidexam.presentation.view.InitialActivity;
import com.example.userprofileandroidexam.presentation.view.UserListActivity;
import com.example.userprofileandroidexam.presentation.view.UserProfileActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    public abstract UserListActivity contributeMainActivity();

    @ContributesAndroidInjector
    public abstract InitialActivity contributeInitialActivity();

    @ContributesAndroidInjector
    public abstract UserProfileActivity contributeUserProfileActivity();
}
