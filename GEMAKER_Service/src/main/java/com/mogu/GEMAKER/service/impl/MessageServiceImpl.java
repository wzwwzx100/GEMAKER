package com.mogu.GEMAKER.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.dao.mapper.MessageDoMapper;
import com.mogu.GEMAKER.dao.mapper.TerminalDoMapper;
import com.mogu.GEMAKER.entity.MessageParam;
import com.mogu.GEMAKER.entity.MsgInfoDo;
import com.mogu.GEMAKER.entity.TerminalDo;
import com.mogu.GEMAKER.service.MessageService;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDoMapper messageDoMapper;


    @Override
    public BizResult addMessage(MsgInfoDo msgInfoDo) {
        int ret = messageDoMapper.addMessage(msgInfoDo);
        if(ret == 1){
            return BizResult.success();
        }
        return BizResult.error("保存失败！");
    }

    @Override
    public BizResult lst(MessageParam msgInfoDo) {
        String orderBy = "id desc";
        PageHelper.startPage(msgInfoDo.getPageNum(),msgInfoDo.getPageSize(),orderBy);
        List<MsgInfoDo> lst = messageDoMapper.lst(msgInfoDo);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }

}