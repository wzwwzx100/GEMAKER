package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.entity.MessageDo;
import com.mogu.GEMAKER.entity.TerminalDo;
import com.mogu.GEMAKER.entity.TerminalParam;
import com.mogu.GEMAKER.util.BizResult;

public interface TerminalService {

    BizResult register(TerminalDo terminal);


    BizResult modify(TerminalDo terminal);

    BizResult delete(TerminalDo terminal);

    BizResult lst(int pageNum,int pageSize,TerminalDo terminalDo);

    BizResult offLine(int pageNum,int pageSize,TerminalParam terminalDo);

    TerminalDo findById(String id);


    BizResult issueConfig(TerminalDo terminalDo);

    BizResult resendCommand(TerminalDo terminalDo);

    MessageDo getCommand(TerminalDo terminalDo);

    BizResult successCommand(Long cmd_id);


}
