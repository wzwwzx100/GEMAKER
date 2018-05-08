package com.mogu.GEMAKER.model.params;

import com.mogu.GEMAKER.model.entity.TemplateDo;

public class TemplatePara extends TemplateDo {
    private Integer pageSize;
    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
