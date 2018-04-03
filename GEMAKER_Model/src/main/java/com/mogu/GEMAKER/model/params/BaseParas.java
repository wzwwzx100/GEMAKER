package com.mogu.GEMAKER.model.params;

import java.io.Serializable;

/**
 * Created by chang on 2017/6/16.
 */
public class BaseParas implements Serializable{
//    private String token;
    private boolean success = false;
    private String message;
    private String sign;

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean valid(){
        return false;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
