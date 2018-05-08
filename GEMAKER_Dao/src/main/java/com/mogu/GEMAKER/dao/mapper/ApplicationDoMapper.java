package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.ApplicationDo;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDoMapper {
    int save(ApplicationDo app);

    int update(ApplicationDo app);

    ApplicationDo get(ApplicationDo applicationDo);


    @Select("select * from tbl_application")
    @ResultMap("BaseResultMap")
    List<ApplicationDo> lst();
}
