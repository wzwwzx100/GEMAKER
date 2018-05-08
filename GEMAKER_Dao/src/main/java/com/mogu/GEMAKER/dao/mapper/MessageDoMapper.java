package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.params.MessageParam;
import com.mogu.GEMAKER.model.entity.MsgInfoDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDoMapper {
    int addMessage(MsgInfoDo msgInfoDo);

    List<MsgInfoDo> lst(MessageParam msgInfoDo);
}
