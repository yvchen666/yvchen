package com.nszx.bbs.bean;

public class List_Bean {
    public List_Bean(int use_head, int star, String item_title, String item_message) {
        this.use_head = use_head;
        this.star = star;
        this.item_title = item_title;
        this.item_message = item_message;

    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    private int star;

    public int getUse_head() {
        return use_head;
    }

    public void setUse_head(int use_head) {
        this.use_head = use_head;
    }

    private int use_head;


    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    private String item_title;

    public String getItem_message() {
        return item_message;
    }

    public void setItem_message(String item_message) {
        this.item_message = item_message;
    }

    private String item_message;
}
