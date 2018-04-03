package com.mogu.GEMAKER.model.common;


/**
 * Created by chang on 2017/7/25.
 */
public class PosGroup {
    private Long bg;
    private Long ed;
    int isfirt=0 ;

    public PosGroup(){}

    public PosGroup(Long bg, Long ed) {
        this.bg = bg;
        this.ed = ed;
    }

    public Long getBg() {
        return bg;
    }

    public void setBg(Long bg) {
        this.bg = bg;
    }

    public Long getEd() {
        return ed;
    }

    public void setEd(Long ed) {
        this.ed = ed;
    }

    public int getIsfirt() {
        return isfirt;
    }

    public void setIsfirt(int isfirt) {
        this.isfirt = isfirt;
    }
}
