package com.example.slowword.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.slowword.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User... user);


    @Update
    void updateUser(User... user);

    // 根据对象删除
    @Delete()
    void deleteUser(User... user);

    // 全部删除
    @Query("DELETE FROM users")
    void deleteAllUser();

    // 查询所有
    @Query("SELECT * FROM users")
    List<User> getAllUser();


    // 条件查询
    @Query("SELECT * FROM users WHERE username=:username AND password=:password")
    User getUser(String username,String password);
}
