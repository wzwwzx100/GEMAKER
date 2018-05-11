package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.entity.CommandDo;
import com.mogu.GEMAKER.model.entity.MessageDo;
import com.mogu.GEMAKER.model.entity.TemplateDo;
import com.mogu.GEMAKER.model.entity.TerminalDo;
import com.mogu.GEMAKER.util.BizResult;

import java.util.List;

public interface CommandService {

    BizResult add(CommandDo commandDo);

    BizResult modify(CommandDo commandDo);

    BizResult success(Long id,Integer flag);

    BizResult del(CommandDo commandDo);

    BizResult send(CommandDo commandDo);

    MessageDo createMessage(CommandDo commandDo) throws Exception;


    BizResult lst(int pageNum,int pageSize,CommandDo commandDo);

    List<CommandDo> findFailed(TerminalDo terminal);


    BizResult readTemplate(TemplateDo templateDo);

}
