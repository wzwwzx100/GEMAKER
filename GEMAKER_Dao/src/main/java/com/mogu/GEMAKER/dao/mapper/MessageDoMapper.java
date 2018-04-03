package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.entity.MessageParam;
import com.mogu.GEMAKER.entity.MsgInfoDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDoMapper {
    int addMessage(MsgInfoDo msgInfoDo);

    List<MsgInfoDo> lst(MessageParam msgInfoDo);
}
