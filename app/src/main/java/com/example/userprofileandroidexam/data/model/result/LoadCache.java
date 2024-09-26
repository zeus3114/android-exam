package com.example.userprofileandroidexam.data.model.result;

import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;
import com.example.userprofileandroidexam.domain.LoadDataUserState;

import java.util.List;

public class LoadCache {

    private List<LocalUserSavedDataModel> loadLocalUser;
    private LoadDataUserState loadDataUserState;

    public LoadCache(List<LocalUserSavedDataModel> loadLocalUser, LoadDataUserState loadDataUserState) {
        this.loadLocalUser = loadLocalUser;
        this.loadDataUserState = loadDataUserState;
    }

    public List<LocalUserSavedDataModel> getLoadLocalUser() {
        return loadLocalUser;
    }

    public LoadDataUserState getLoadDataUserState() {
        return loadDataUserState;
    }

    public LoadCache(LoadDataUserState loadDataUserState) {
        this.loadDataUserState = loadDataUserState;
    }
}
