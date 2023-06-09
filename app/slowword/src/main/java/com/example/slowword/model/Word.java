package com.example.slowword.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "words")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    private int id;
    @ColumnInfo(name = "english", typeAffinity = ColumnInfo.TEXT)
    private String english;
    @ColumnInfo(name = "chinese", typeAffinity = ColumnInfo.TEXT)
    private String chinese;

    @Ignore
    public Word() {}

    public Word(int id, String english, String chinese) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
    }

    @Ignore
    public Word(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", english='" + english + '\'' +
                ", chinese='" + chinese + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id && english.equals(word.english) && chinese.equals(word.chinese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, english, chinese);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
