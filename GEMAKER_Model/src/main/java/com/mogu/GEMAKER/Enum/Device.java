package com.mogu.GEMAKER.Enum;

import com.mogu.GEMAKER.util.BASE64Util;

/**
 * Created by chang on 2017/6/21.
 */
public enum  Device {
    WEB(0,"web", BASE64Util.BASE64("web")),
    IOS(1,"ios", BASE64Util.BASE64("laorenjiaios")),
    ANDROID(2,"android", BASE64Util.BASE64("android")),


    ;
    private Integer code;
    private String name;
    private String sign;

    Device(Integer code, String name, String sign) {
        this.code = code;
        this.name = name;
        this.sign = sign;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public static boolean signValid(String var1){
        Device[] all = Device.values();
        for (Device o:all){
            if (var1.equals(o.getSign()))return true;
        }
        return false;
    }

    public static Device getDevice(String var1){
        Device[] all = Device.values();
        for (Device o:all){
            if (var1.equals(o.getSign()))return o;
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(BASE64Util.BASE64("web"));
    }
}
