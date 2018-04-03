package com.mogu.GEMAKER.Enum;


import java.util.regex.Pattern;

/**
 * Created by chang on 2017/7/6.
 */
public enum  Reason {
    MILEAGE_FOLLOWING_1K(0,"米下",1,true,"0,1000"),
    MILEAGE_FOLLOWING_2K(1,"米下",1,true,"1000,1500"),
    MILEAGE_FOLLOWING_4K(2,"米下",1,true,"1500,4000"),
    MILEAGE_FOLLOWING_UP(3,"米下",1,true,"4000,-1"),
    ;
    private Integer code;
    private String des;//描述
    private Integer score;//分值

    private boolean diff;//是否有对比值
    private String pattern;//对比正则

    Reason(Integer code, String des, Integer score, boolean diff, String pattern) {
        this.code = code;
        this.des = des;
        this.score = score;
        this.diff = diff;
        this.pattern = pattern;
    }

    public Integer getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }

    public Integer getScore() {
        return score;
    }

    public boolean isDiff() {
        return diff;
    }

    public String getPattern() {
        return pattern;
    }

    public static void main(String[] args) {

    }
}
