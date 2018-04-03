package com.mogu.GEMAKER.Enum;

/**
 * Created by chang on 2017/6/26.
 */
public enum  PublisherEnum {
    OFFICIAL(0,"老人·家 官方发布"),
    PERSONAL(1,"老人·家 个人发布"),
    THIRD(2,"老人·家 个人发布")
            ;
    private Integer code;
    private String des;

     PublisherEnum(Integer code, String des) {
        this.code = code;
        this.des = des;
    }

    public Integer getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
