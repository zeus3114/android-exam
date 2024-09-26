package com.example.userprofileandroidexam.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.userprofileandroidexam.data.model.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}