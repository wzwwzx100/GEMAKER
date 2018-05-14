package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.params.MessageParam;
import com.mogu.GEMAKER.model.entity.MsgInfoDo;
import com.mogu.GEMAKER.util.BizResult;

public interface MessageService {

    BizResult addMessage(MsgInfoDo msgInfoDo);


    BizResult lst(MessageParam msg);
}
