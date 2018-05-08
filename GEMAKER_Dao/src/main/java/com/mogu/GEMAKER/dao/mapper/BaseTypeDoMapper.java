package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.BaseTypeDo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BaseTypeDoMapper {
    int register(BaseTypeDo baseTypeDo);

    int delete(Integer id);

    int modify(BaseTypeDo baseTypeDo);

    BaseTypeDo findByCode(String code);

    BaseTypeDo findById(BaseTypeDo baseTypeDo);

    List<BaseTypeDo> lst(BaseTypeDo baseTypeDo);
}
