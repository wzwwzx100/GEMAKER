package com.mogu.GEMAKER.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.Enum.ResultCode;
import com.mogu.GEMAKER.constants.CommonConstant;
import com.mogu.GEMAKER.dao.mapper.*;
import com.mogu.GEMAKER.entity.*;
import com.mogu.GEMAKER.model.common.SystemConfig;
import com.mogu.GEMAKER.service.CommandService;
import com.mogu.GEMAKER.service.MessageTypeService;
import com.mogu.GEMAKER.service.SystemConfigService;
import com.mogu.GEMAKER.service.TerminalService;
import com.mogu.GEMAKER.service.net.UDPClient;
import com.mogu.GEMAKER.util.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Protocol;
import sun.management.Sensor;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TerminalServiceImpl implements TerminalService{


    @Autowired
    private TerminalDoMapper terminalDoMapper;

    @Autowired
    private SensorDoMapper sensorDoMapper;

    @Autowired
    private DataDoMapper dataDoMapper;

    @Autowired
    private MessageTypeDoMapper messageTypeDoMapper;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private CommandService commandService;

    private static final Logger log = LoggerFactory.getLogger(TerminalServiceImpl.class);
    @Override
    public BizResult register(TerminalDo terminal) {
        terminal.setKeyt(CommonConstant.KEY);
        TerminalDo exist = terminalDoMapper.findById(terminal.getId());
        if(exist != null){
            return BizResult.error(ResultCode.CODE_EXIST);
        }
        int ret = terminalDoMapper.register(terminal);
        if(ret == 1){
            return BizResult.success();
        }
        return BizResult.error("注册失败，请重试！");
    }

    @Override
    public BizResult modify(TerminalDo terminal) {
        int ret = terminalDoMapper.modify(terminal);
        if(ret == 1){
            return BizResult.success();
        }
        return BizResult.error("修改失败，请重试！");
    }

    @Override
    public BizResult delete(TerminalDo terminal) {
        int ret = terminalDoMapper.modify(terminal);
        if(ret == 1){
            return BizResult.success();
        }
        return BizResult.error("删除失败，请重试！");
    }

    @Override
    public BizResult lst(int pageNum, int pageSize,TerminalDo terminalDo) {
        String orderBy = "id asc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<TerminalDo> lst = terminalDoMapper.lst(terminalDo);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }

    @Override
    public BizResult offLine(int pageNum, int pageSize, TerminalParam terminalDo) {
        String orderBy = "id asc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        terminalDo.setTime(terminalDo.getTime()*60);
        List<TerminalDo> lst = terminalDoMapper.offLine(terminalDo);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }

    @Override
    public TerminalDo findById(String id) {
        return terminalDoMapper.findById(id);
    }

    @Override
    public BizResult issueConfig(TerminalDo terminalDo) {
        terminalDo = findById(terminalDo.getId());
        Date now = new Date();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MessageTypeDo messageTypeDo = (MessageTypeDo) messageTypeDoMapper.findByCode("1143");
        List<SensorDo> sensors = sensorDoMapper.lst(terminalDo.getId());
        SystemConfig systemConfig = systemConfigService.findByCode("heart");
        UDPClient udp = new UDPClient(terminalDo.getIp(),terminalDo.getPort());
        for(int i=0;i<sensors.size();i++){
            SensorDo item = sensors.get(i);
            CommandDo commandDo = new CommandDo();
            commandDo.setMessageType(messageTypeDo);
            commandDo.setTerminal(terminalDo);
            commandDo.setTime(now);
            Map<String,Object> jsonMsg = new HashMap<>();
            if(i == 0){
                jsonMsg.put("flag",1);
            }else{
                jsonMsg.put("flag",2);
            }
            jsonMsg.put("id",item.getFactory());
            jsonMsg.put("type",item.getType().getCode());
            jsonMsg.put("model",item.getModel());
            jsonMsg.put("ch",item.getChannel());
            jsonMsg.put("sampling",item.getFrequency());
            jsonMsg.put("reserved",item.getText());
            List<DataDo> dataDoList = dataDoMapper.lst(item.getId());
            if(dataDoList != null && !dataDoList.isEmpty()){
                jsonMsg.put("count",dataDoList.size());
                List<Map<String,Object>> datas = new ArrayList<>();
                for(DataDo data : dataDoList){
                    Map<String,Object> map = new HashMap<>();
                    map.put("type",data.getDataType().getCode());
                    map.put("id",data.getId());
                    datas.add(map);
                }
                jsonMsg.put("datas", datas);
            }else{
                jsonMsg.put("count",0);
                jsonMsg.put("datas", new ArrayList<>());
            }
            try {
                commandDo.setJsonMsg(mapper.writeValueAsString(jsonMsg));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(),e);
                return BizResult.error();
            }
            commandDo.setStatus(0);
            commandService.add(commandDo);
            //查询设备是否离线
            Date start = terminalDo.getLastTime();
            if(start != null && (start.getTime() + Long.parseLong(systemConfig.getValue())*1000) >= now.getTime() ){
                commandService.send(commandDo);
            }
        }
        return BizResult.success();
    }


    @Override
    public BizResult resendCommand(TerminalDo terminalDo) {
        List<CommandDo> cmds = commandService.findFailed(terminalDo);
        if(cmds != null && !cmds.isEmpty()){
            for(CommandDo item : cmds){
                commandService.send(item);
            }
        }
        return BizResult.success();
    }


    @Override
    public MessageDo getCommand(TerminalDo terminalDo) {
        List<CommandDo> cmds = commandService.findFailed(terminalDo);
        if(cmds != null && !cmds.isEmpty()){
            try{
                return commandService.createMessage(cmds.get(0));
            }catch (Exception e){
                log.error(e.getMessage(),e);
                return null;
            }
        }
        return null;
    }

    @Override
    public BizResult successCommand(Long cmd_id) {
        return commandService.success(cmd_id);
    }

}
