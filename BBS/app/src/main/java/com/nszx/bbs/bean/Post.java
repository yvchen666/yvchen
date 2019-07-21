package com.nszx.bbs.bean;

import com.nszx.bbs.bean.MyUser;

import cn.bmob.v3.*;
import cn.bmob.v3.datatype.*;

public class Post extends BmobObject
{
    //贴子标题
    private String title;
    //贴子内容
    private String Message;
    //帖子作者名

    private MyUser authors;
    //贴子板块
    private String string_bankuai;
    //审核贴纸
    private Boolean shenhe;
    //版主认证
    private Boolean renzheng;
    //首页帖子
    private String home;
    private BmobRelation likes;
    //收藏帖子

    public void setLikes(BmobRelation likes)
    {
        this.likes = likes;
    }

    public BmobRelation getLikes()
    {
        return likes;
    }

    public void setHome(String home)
    {
        this.home = home;
    }

    public String getHome()
    {
        return home;
    }

    public void setAuthors(MyUser authors)
    {
        this.authors = authors;
    }

    public MyUser getAuthors()
    {
        return authors;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setMessage(String message)
    {
        Message = message;
    }

    public String getMessage()
    {
        return Message;
    }

    public void setBk(String bk)
    {
        this.string_bankuai = bk;
    }

    public String getBk()
    {
        return string_bankuai;
    }

    public void setSh(Boolean sh)
    {
        this.shenhe = sh;
    }

    public Boolean getSh()
    {
        return shenhe;
    }

    public void setRenzheng(Boolean renzheng)
    {
        this.renzheng = renzheng;
    }

    public Boolean getRenzheng()
    {
        return renzheng;
    }
}
