package com.mogu.GEMAKER.model.vo;


import java.io.Serializable;

public class PreResultVo implements Serializable {

    private Integer index;
    private Double val;


    public PreResultVo(Integer index, Double val) {
        this.index = index;
        this.val = val;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }
}
