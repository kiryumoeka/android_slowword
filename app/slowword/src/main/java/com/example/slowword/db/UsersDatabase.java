package com.example.slowword.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.slowword.model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UsersDatabase extends RoomDatabase {
    private static final String DATABSE_NAME = "slowword_db.db";
    private static UsersDatabase mInstance;
    public abstract UserDao getUserDao();
    public static synchronized UsersDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),UsersDatabase.class,DATABSE_NAME).allowMainThreadQueries().build();
        }
        return mInstance;
    }
}
