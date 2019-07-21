package com.nszx.bbs.bean;

import cn.bmob.v3.BmobUser;

public class UserBean extends BmobUser {
    private String token;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    private String imei;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
