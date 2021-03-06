package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.TerminalDo;
import com.mogu.GEMAKER.model.params.TerminalParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalDoMapper {

    TerminalDo findById(String id);

    int register(TerminalDo terminal);

    int modify(TerminalDo terminal);

    int delete(TerminalDo terminal);


    List<TerminalDo> lst(TerminalDo terminalDo);

    List<TerminalDo> offLine(TerminalParam terminalDo);




}
