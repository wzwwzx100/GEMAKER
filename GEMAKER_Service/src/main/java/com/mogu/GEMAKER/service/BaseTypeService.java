package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.entity.BaseTypeDo;
import com.mogu.GEMAKER.util.BizResult;

public interface BaseTypeService {

    BizResult get(BaseTypeDo baseTypeDo);

    BizResult register(BaseTypeDo baseTypeDo);

    BizResult delete(Integer id);

    BizResult modify(BaseTypeDo baseTypeDo);

    BizResult lst(Integer pageNum,Integer pageSize,BaseTypeDo baseTypeDo);
}
