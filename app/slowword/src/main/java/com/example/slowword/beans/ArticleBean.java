package com.example.slowword.beans;

/**
 * 给RecyclerView适配器用的
 */
public class ArticleBean {

    public int id;
    public String title;
    public String detail;

    public ArticleBean(int id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public ArticleBean() {
    }
}
