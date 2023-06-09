package com.example.slowword.beans;

/**
 * 给RecyclerView适配器用的
 */
public class WordBean {
    public String english;
    public String chinese;
    public int id;

    public WordBean(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public WordBean(String english, String chinese, int id) {
        this.english = english;
        this.chinese = chinese;
        this.id = id;
    }
}
