package com.mogu.GEMAKER.model.params;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by chang on 2017/6/21.
 */
public class UpdUserInfoPara {

    private String userName;

    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean verify(){
        if (StringUtils.isNotEmpty(userName) && userName.trim().length() > 12){
            this.message = "用户名要求12字以内";
            return true;
        }
        return false;
    }
}
