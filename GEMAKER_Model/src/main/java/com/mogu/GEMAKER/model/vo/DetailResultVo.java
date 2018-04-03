package com.mogu.GEMAKER.model.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailResultVo {
    private String key;
    private Integer num;
    private Map<String,Integer> pbi;


    public DetailResultVo() {
    }

    public DetailResultVo(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Map getPbi() {
        if(pbi == null){
            pbi = new HashMap<>();
        }
        return pbi;
    }

    public void setPbi(Map pbi) {
        this.pbi = pbi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailResultVo that = (DetailResultVo) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public void add(){
        this.num++;
    }
}
