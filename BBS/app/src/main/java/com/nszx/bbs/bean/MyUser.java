package com.nszx.bbs.bean;
import cn.bmob.v3.*;
import cn.bmob.v3.datatype.*;

public class MyUser extends BmobUser
{
    private Boolean Is;//封号
    private Boolean vip;
    private String qm;
    private BmobFile icon;
    private String tx;
    private String UserStyle;

    public void setUserStyle(String userStyle)
    {
        UserStyle = userStyle;
    }

    public String getUserStyle()
    {
        return UserStyle;
    }

    @Override
    public String toString()
    {
        return "user{" +
                "Is=" + Is +
                ", vip=" + vip +
                ", qm='" + qm + '\'' +
                ", icon=" + icon +
                ", tx='" + tx + '\'' +
                '}';
    }

    public void setIcon(BmobFile icon)
    {
        this.icon = icon;
    }

    public BmobFile getIcon()
    {
        return icon;
    }

    public void setTx(String tx)
    {
        this.tx = tx;
    }

    public String getTx()
    {
        return tx;
    }

    public void setQm(String qm)
    {
        this.qm = qm;
    }

    public String getQm()
    {
        return qm;
    }

    public void setVip(Boolean vip)
    {
        this.vip = vip;
    }

    public Boolean getVip()
    {
        return vip;
    }

    public void setIs(Boolean is)
    {
        Is = is;
    }

    public Boolean getIs()
    {
        return Is;
    }
}
