package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.entity.ResultDo;
import com.mogu.GEMAKER.model.entity.TerminalDo;
import com.mogu.GEMAKER.util.BizResult;

public interface ResultService {
    BizResult lst(int pageNum,int pageSize,ResultDo result);

    BizResult save(ResultDo result);

    BizResult realValue(TerminalDo terminalDo);

    BizResult updateRealTime(ResultDo resultDo);
}
