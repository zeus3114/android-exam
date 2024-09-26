package com.example.userprofileandroidexam.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.userprofileandroidexam.data.model.LocalUserSavedDataModel;
import com.example.userprofileandroidexam.data.model.entity.UserEntity;

import java.util.List;


@Dao
public interface UserDao {

    @Insert
    void insertUser(UserEntity userEntity);

    @Query("SELECT * FROM tblUser ORDER BY id ASC")
    List<LocalUserSavedDataModel> getAllUsers();

    @Query("DELETE FROM tblUser")
    void clearAllUsers();
}
