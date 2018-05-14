package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.ResultDo;
import com.mogu.GEMAKER.model.entity.TerminalDo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResultDoMapper {

    int save(ResultDo resultDo);

    List<ResultDo> lst(ResultDo resultDo);

    List<ResultDo> realValue(TerminalDo terminalDo);

    int updateReal(ResultDo resultDo);
}
