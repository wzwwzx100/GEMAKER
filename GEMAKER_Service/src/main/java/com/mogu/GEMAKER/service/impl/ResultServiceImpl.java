package com.mogu.GEMAKER.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.dao.mapper.DataDoMapper;
import com.mogu.GEMAKER.dao.mapper.ResultDoMapper;
import com.mogu.GEMAKER.dao.mapper.SensorDoMapper;
import com.mogu.GEMAKER.dao.mapper.TerminalDoMapper;
import com.mogu.GEMAKER.entity.DataDo;
import com.mogu.GEMAKER.entity.ResultDo;
import com.mogu.GEMAKER.entity.SensorDo;
import com.mogu.GEMAKER.entity.TerminalDo;
import com.mogu.GEMAKER.service.ResultService;
import com.mogu.GEMAKER.util.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultDoMapper resultDoMapper;

    @Autowired
    private DataDoMapper dataDoMapper;

    @Autowired
    private SensorDoMapper sensorDoMapper;

    private static final Logger log = LoggerFactory.getLogger(ResultService.class);


    @Override
    public BizResult lst(int pageNum, int pageSize, ResultDo result) {
        String orderBy = "time desc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<ResultDo> lst = resultDoMapper.lst(result);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }

    @Override
    public BizResult save(ResultDo result) {
        DataDo dataDo = dataDoMapper.findById(result.getData().getId());
        if(dataDo == null){
            log.error("配置信息错误。数据ID：["+result.getData().getId()+"]不存在！");
            return  BizResult.error(result.getData().getId().toString());
        }
        SensorDo sensorDo = new SensorDo();
        sensorDo.setId(dataDo.getSensor());
        //更新last_time
        //sensor
        sensorDo.setLastTime(result.getTime());
        sensorDoMapper.update(sensorDo);
        //data
        dataDo.setLastTime(result.getTime());
        dataDoMapper.update(dataDo);
        result.setSensor(sensorDo);
        return resultDoMapper.save(result) == 1? BizResult.success() : BizResult.error("保存失败！");
    }

    @Override
    public BizResult realValue(TerminalDo terminalDo) {
        return BizResult.success(resultDoMapper.realValue(terminalDo));
    }
}
