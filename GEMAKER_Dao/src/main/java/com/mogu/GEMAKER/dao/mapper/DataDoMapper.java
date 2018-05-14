package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.DataDo;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
@Repository
public interface DataDoMapper {
    int register(DataDo dataDo);

    List<DataDo> lst(Long sensor);

    DataDo findById(Long id);
    int delete(DataDo dataDo);

    List<DataDo> lstbyterminal(String terminal);

    int update(DataDo dataDo);
}
