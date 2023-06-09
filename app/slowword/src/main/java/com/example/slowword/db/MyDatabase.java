package com.example.slowword.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.slowword.model.Arcticle;
import com.example.slowword.model.User;
import com.example.slowword.model.Word;

/**
 * 创建数据库，并且根据model层的类生成数据库表，且返回不同表的dao对象
 */
@Database(entities = {Word.class, User.class, Arcticle.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static final String DATABSE_NAME = "slowword_db.db";
    private static MyDatabase mInstance;
    public abstract WordDao getWordDao();
    public abstract UserDao getUserDao();
    public abstract ArcticleDao getArcticleDao();
    public static synchronized MyDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,DATABSE_NAME).addMigrations().allowMainThreadQueries().build();
        }
        return mInstance;
    }
}
