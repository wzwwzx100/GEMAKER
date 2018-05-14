package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.CommandDo;
import com.mogu.GEMAKER.model.entity.TerminalDo;
import com.mogu.GEMAKER.util.BizResult;

import java.util.List;

public interface CommandDoMapper {
    int add(CommandDo commandDo);


    int modify(CommandDo commandDo);

    int del(Long id);

    int success(Long id);

    List<CommandDo> lst(CommandDo commandDo);

    List<CommandDo> findFailed(TerminalDo terminal);

}
