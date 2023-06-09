package com.example.slowword.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.slowword.model.Arcticle;
import com.example.slowword.model.User;

import java.util.List;

/**
 * 由MyDatabase生成实现类
 */
@Dao
public interface ArcticleDao {
    @Insert
    void insertArcticle(Arcticle... arcticle);


    @Update
    void updateArcticle(Arcticle... arcticle);

    // 根据对象删除
    @Delete()
    void deleteArcticle(Arcticle... arcticle);

    // 全部删除
    @Query("DELETE FROM arcticles")
    void deleteAllArcticle();

    // 查询所有
    @Query("SELECT * FROM arcticles")
    List<Arcticle> getAllArcticle();

}
