package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.entity.DataDo;
import com.mogu.GEMAKER.util.BizResult;

public interface DataService {
    BizResult register(DataDo dataDo);
    BizResult delete(DataDo dataDo);
    BizResult modify(DataDo dataDo);
    BizResult lst(Long sensor);
    BizResult findById(DataDo dataDo);
}
