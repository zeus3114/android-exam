package com.example.userprofileandroidexam.presentation.viewmodel;

import static com.example.userprofileandroidexam.helper.Constants.PAGE_INDEX;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userprofileandroidexam.data.local.AppDatabase;
import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;
import com.example.userprofileandroidexam.data.model.entity.UserEntity;
import com.example.userprofileandroidexam.data.model.response.UserResponse;
import com.example.userprofileandroidexam.data.model.result.Error;
import com.example.userprofileandroidexam.data.model.result.LoadCache;
import com.example.userprofileandroidexam.domain.ErrorState;
import com.example.userprofileandroidexam.data.repository.UserProfileDataRepository;
import com.example.userprofileandroidexam.domain.LoadDataUserState;
import com.example.userprofileandroidexam.domain.RandomUserState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserProfileViewModel extends ViewModel implements UserProfileViewModelHelper {
    private static final String TAG = UserProfileViewModel.class.getSimpleName();

    private UserProfileDataRepository userProfileDataRepository;
    private AppDatabase appDatabase;
    private List<LocalUserSavedDataModel> localUserSavedDataModelList = new ArrayList<>();


    //Mutable live data
    private MutableLiveData<LoadCache> loadCacheMutableLiveData;
    private MutableLiveData<Error> errorMutableLiveData;


    //livedata response
    public LiveData<LoadCache> getloadCacheMutableLiveData() {
        return loadCacheMutableLiveData;
    }

    public LiveData<Error> getErrorAsLiveData() {
        return errorMutableLiveData;
    }


    @Inject
    public UserProfileViewModel(UserProfileDataRepository userProfileDataRepository, AppDatabase appDatabase) {
        this.userProfileDataRepository = userProfileDataRepository;
        this.appDatabase = appDatabase;
        loadCacheMutableLiveData = new MutableLiveData<>();
        errorMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void getRandomUser(RandomUserState randomUserState) {
        if (randomUserState.equals(RandomUserState.RANDOM_STATE_NEXT_PAGE)) {
            PAGE_INDEX++;
        }
        localUserSavedDataModelList.clear();
        userProfileDataRepository.getRandomUser(PAGE_INDEX)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe ", "onSubscribe");
                    }

                    @Override
                    public void onNext(UserResponse userResponse) {
                        Log.e("onNext: ", "onNext" + userResponse.getUserList().size());
                        //insert cache to db
                        for (int i = 0; i < userResponse.getUserList().size(); i++) {
                            appDatabase.getUserDao().insertUser(new UserEntity(0,
                                    userResponse.getUserList().get(i).getFullname(),
                                    userResponse.getUserList().get(i).getEmail(),
                                    userResponse.getUserList().get(i).getAge(),
                                    userResponse.getUserList().get(i).getFullAddress(),
                                    userResponse.getUserList().get(i).getPhone(),
                                    userResponse.getUserList().get(i).getDate(),
                                    userResponse.getUserList().get(i).getLarge(),
                                    userResponse.getPageInfo().getPage()));
                        }
                        localUserSavedDataModelList.addAll(appDatabase.getUserDao().getAllUsers());
                        loadCacheMutableLiveData.postValue(new LoadCache(localUserSavedDataModelList,
                                LoadDataUserState.CACHE_UPDATE));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError ", "Response error: " + e);
                        errorMutableLiveData.setValue(new Error((ErrorState.INTERNET_CONNECTION_ERROR)));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete ", "Response Complete");
                    }
                });
    }

    @Override
    public void getAllUsers() {
        localUserSavedDataModelList.clear();
        if (appDatabase.getUserDao().getAllUsers().size() > 0) {
            Log.e(TAG, "Load cache");
            localUserSavedDataModelList.addAll(appDatabase.getUserDao().getAllUsers());
            loadCacheMutableLiveData.postValue(new LoadCache(localUserSavedDataModelList,
                    LoadDataUserState.LOAD_CACHE));
        } else {
            Log.e(TAG, "Cache not available");
            loadCacheMutableLiveData.postValue(new LoadCache(LoadDataUserState.CACHE_NOT_AVAILABLE));
        }
    }

    @Override
    public void clearCache() {
        localUserSavedDataModelList.addAll(appDatabase.getUserDao().getAllUsers());
        loadCacheMutableLiveData.postValue(new LoadCache(localUserSavedDataModelList,
                LoadDataUserState.FORCE_UPDATE_CACHE));
        Log.e(TAG, "Cache deleted");
        PAGE_INDEX = 1;
        appDatabase.getUserDao().clearAllUsers();
    }
}