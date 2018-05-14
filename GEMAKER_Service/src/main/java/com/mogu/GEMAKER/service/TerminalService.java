package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.entity.MessageDo;
import com.mogu.GEMAKER.model.entity.TerminalDo;
import com.mogu.GEMAKER.model.params.DowndataParam;
import com.mogu.GEMAKER.model.params.SwitchParam;
import com.mogu.GEMAKER.model.params.TerminalParam;
import com.mogu.GEMAKER.util.BizResult;

public interface TerminalService {

    BizResult register(TerminalDo terminal);


    BizResult modify(TerminalDo terminal);

    BizResult delete(TerminalDo terminal);

    BizResult lst(Integer pageNum,Integer pageSize,TerminalDo terminalDo);

    BizResult offLine(Integer pageNum,Integer pageSize,TerminalParam terminalDo);

    TerminalDo findById(String id);


    BizResult issueConfig(TerminalDo terminalDo);

    BizResult testSend(TerminalDo terminalDo);

    BizResult resendCommand(TerminalDo terminalDo);

    MessageDo getCommand(TerminalDo terminalDo);

    BizResult successCommand(Long cmd_id);

    BizResult switcher(SwitchParam switchParam);

    BizResult downdata(DowndataParam switchParam);

    BizResult lstdata(TerminalDo terminalDo);


}
