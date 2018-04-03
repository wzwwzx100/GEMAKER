package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.common.SystemConfig;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SystemConfigMapper {
    SystemConfig findByCode(String code);
    int add(SystemConfig systemConfig);
    int modify(SystemConfig systemConfig);
    @Select("select * from tbl_config")
    @ResultMap("BaseResultMap")
    List<SystemConfig> lst();
}
