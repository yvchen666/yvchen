package com.nszx.bbs.bean;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论的用户
     */
    private UserBean user;

    /**
     * 所评论的帖子
     */
    private Post post;


    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public UserBean getUser() {
        return user;
    }

    public Comment setUser(UserBean user) {
        this.user = user;
        return this;
    }

    public Post getPost() {
        return post;
    }

    public Comment setPost(Post post) {
        this.post = post;
        return this;
    }
}