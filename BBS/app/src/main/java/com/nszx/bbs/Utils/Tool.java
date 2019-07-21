package com.nszx.bbs.Utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.UUID;

public class Tool {
    private String imei;

    public String token() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    public String imei(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
