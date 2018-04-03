package com.mogu.GEMAKER.exceptions;

/**
 * Created by chang on 2017/6/19.
 */
public class NoToken extends RuntimeException{
    public NoToken(){
        super("没有授权令牌");
    }
}
