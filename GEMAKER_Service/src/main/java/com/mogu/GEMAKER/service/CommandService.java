package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.entity.CommandDo;
import com.mogu.GEMAKER.entity.MessageDo;
import com.mogu.GEMAKER.entity.TerminalDo;
import com.mogu.GEMAKER.util.BizResult;

import java.util.List;

public interface CommandService {

    BizResult add(CommandDo commandDo);

    BizResult success(Long id);

    BizResult del(CommandDo commandDo);

    BizResult send(CommandDo commandDo);

    MessageDo createMessage(CommandDo commandDo) throws Exception;


    BizResult lst(int pageNum,int pageSize,CommandDo commandDo);

    List<CommandDo> findFailed(TerminalDo terminal);

}
