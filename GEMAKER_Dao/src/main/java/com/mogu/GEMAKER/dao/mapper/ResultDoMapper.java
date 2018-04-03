package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.entity.ResultDo;
import com.mogu.GEMAKER.entity.TerminalDo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResultDoMapper {

    int save(ResultDo resultDo);

    List<ResultDo> lst(ResultDo resultDo);

    List<ResultDo> realValue(TerminalDo terminalDo);
}
