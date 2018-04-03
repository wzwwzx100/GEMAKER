package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.entity.MessageParam;
import com.mogu.GEMAKER.entity.MsgInfoDo;
import com.mogu.GEMAKER.entity.ResultDo;
import com.mogu.GEMAKER.entity.TerminalDo;
import com.mogu.GEMAKER.util.BizResult;

public interface MessageService {

    BizResult addMessage(MsgInfoDo msgInfoDo);


    BizResult lst(MessageParam msg);
}
