package com.example.slowword.db;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.slowword.model.Word;

import java.util.List;


@Dao
public interface WordDao {

    @Insert
    void insertWord(Word... word);


    @Update
    void updateWord(Word... word);

    // 根据对象删除
    @Delete()
    void deleteWord(Word... word);

    // 全部删除
    @Query("DELETE FROM words")
    void deleteAllWord();

    // 查询所有
    @Query("SELECT * FROM words")
    List<Word> getAllWord();


}
