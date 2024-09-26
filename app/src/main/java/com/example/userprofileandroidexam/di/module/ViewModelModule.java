package com.example.userprofileandroidexam.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.userprofileandroidexam.ViewModelProviderFactory;
import com.example.userprofileandroidexam.di.ViewModelKey;
import com.example.userprofileandroidexam.presentation.viewmodel.UserProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    public abstract ViewModel bindUserProfileViewModel(UserProfileViewModel userProfileViewModel);


    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(
            ViewModelProviderFactory viewModelProviderFactory
    );
}
