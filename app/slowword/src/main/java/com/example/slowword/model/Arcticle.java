package com.example.slowword.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * model的类是操作数据库用的
 */
@Entity(tableName = "arcticles")
public class Arcticle {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @ColumnInfo(name = "iconid", typeAffinity = ColumnInfo.INTEGER)
    private int iconId;

    @ColumnInfo(name = "title", typeAffinity = ColumnInfo.TEXT)
    private String title;
    @ColumnInfo(name = "detail", typeAffinity = ColumnInfo.TEXT)
    private String detail;

    @Ignore
    public Arcticle(int iconId, String title, String detail) {
        this.iconId = iconId;
        this.title = title;
        this.detail = detail;
    }

    public Arcticle(int id, int iconId, String title, String detail) {
        this.id = id;
        this.iconId = iconId;
        this.title = title;
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arcticle arcticle = (Arcticle) o;
        return id == arcticle.id && iconId == arcticle.iconId && title.equals(arcticle.title) && detail.equals(arcticle.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iconId, title, detail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
