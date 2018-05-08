package com.mogu.GEMAKER.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.Enum.ResultCode;
import com.mogu.GEMAKER.constants.CommonConstant;
import com.mogu.GEMAKER.dao.mapper.*;
import com.mogu.GEMAKER.model.entity.*;
import com.mogu.GEMAKER.model.params.DowndataParam;
import com.mogu.GEMAKER.model.params.SwitchParam;
import com.mogu.GEMAKER.model.params.TerminalParam;
import com.mogu.GEMAKER.service.CommandService;
import com.mogu.GEMAKER.service.ResultService;
import com.mogu.GEMAKER.service.SystemConfigService;
import com.mogu.GEMAKER.service.TerminalService;
import com.mogu.GEMAKER.util.BizResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
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

    @Resource
    private ResultService resultService;


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
    public BizResult lst(Integer pageNum, Integer pageSize,TerminalDo terminalDo) {
        if(pageNum == null || pageSize == null){
            return BizResult.success(terminalDoMapper.lst(terminalDo));
        }else{
            String orderBy = "id asc";
            PageHelper.startPage(pageNum,pageSize,orderBy);
            List<TerminalDo> lst = terminalDoMapper.lst(terminalDo);
            PageInfo pageInfo = new PageInfo(lst);
            return BizResult.success(pageInfo);
        }
    }

    @Override
    public BizResult offLine(Integer pageNum, Integer pageSize, TerminalParam terminalDo) {
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
    public BizResult testSend(TerminalDo terminalDo) {
        terminalDo = findById(terminalDo.getId());
        Date now = new Date();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MessageDo messageDo = new MessageDo();
        messageDo.setCv(Double.parseDouble(systemConfigService.findByCode("cv").getValue()));
        messageDo.setSv(Double.parseDouble(systemConfigService.findByCode("sv").getValue()));
        messageDo.setMid("2013");
        messageDo.setSid("000000000000");
        messageDo.setLife(-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        messageDo.setTs(sdf.format(now));
        Map<String,Object> msgMap = new HashMap<>();
        msgMap.put("cmd_id","1");
        Map<Long,String> rcds = new HashMap();
        rcds.put(1l,"test");
        msgMap.put("rcds",rcds);

        try {
            String jsonMsg = mapper.writeValueAsString(msgMap);
            messageDo.setMsg(jsonMsg);
            messageDo.setSq(1L);
            messageDo.Encrypt(terminalDo.getKeyt());
            ChannelHandlerContext ctx = CommonConstant.udp_link.get(terminalDo.getId());
            if(ctx != null){
                DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer(mapper.writeValueAsString(messageDo).getBytes()),new InetSocketAddress(terminalDo.getIp(),terminalDo.getPort()));
                ctx.writeAndFlush(dp);
            }else{
                return BizResult.success();
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return BizResult.success();
    }

    @Override
    public BizResult issueConfig(TerminalDo terminalDo) {
        terminalDo = findById(terminalDo.getId());
        Date now = new Date();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MessageTypeDo messageTypeDo = (MessageTypeDo) messageTypeDoMapper.findByCode("1143");
        List<SensorDo> sensors = sensorDoMapper.lst(terminalDo.getId());
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
            BizResult save = commandService.add(commandDo);
            if(save.equals(BizResult.error())){
                return save;
            }
            BizResult send = commandService.send(commandDo);
            if(save.equals(BizResult.error())){
                return send;
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

    @Override
    public BizResult switcher(SwitchParam switchParam) {
        //检查设备是否离线
        Date now = new Date();
        DataDo dataDo = dataDoMapper.findById(switchParam.getData());
        SensorDo sensorDo = new SensorDo();
        sensorDo.setId(dataDo.getSensor());
        sensorDo = sensorDoMapper.findById(sensorDo);
        Integer frequency = sensorDo.getFrequency();
        Date lastTime = dataDo.getLastTime();
        if(now.getTime() - lastTime.getTime() > frequency*60l){//时间间隔大于采集频率
            log.error("off_line data id ："+ dataDo.getId());
            return BizResult.error("设备离线！");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            MessageTypeDo messageTypeDo = (MessageTypeDo) messageTypeDoMapper.findByCode("2013");
            TerminalDo terminalDo = findById(switchParam.getTerminal());

            //make command
            CommandDo commandDo = new CommandDo();
            commandDo.setMessageType(messageTypeDo);
            commandDo.setTerminal(terminalDo);
            commandDo.setTime(now);
            commandDo.setStatus(0);
            commandService.add(commandDo);

            Map<String,Object> msgMap = new HashMap<>();
            msgMap.put("cmd_id",commandDo.getId());

            List<Map<String,Object>> rcds = new ArrayList<>();
            Map<String,Object> item = new HashMap<>();
            item.put("id",switchParam.getData());
            item.put("v",switchParam.getSwitcher());
            rcds.add(item);
            msgMap.put("rcds",rcds);
            String jsonMsg = mapper.writeValueAsString(msgMap);
            commandDo.setJsonMsg(jsonMsg);
            MessageDo messageDo = new MessageDo();
            messageDo.setCv(Double.parseDouble(systemConfigService.findByCode("cv").getValue()));
            messageDo.setSv(Double.parseDouble(systemConfigService.findByCode("sv").getValue()));
            messageDo.setMid(commandDo.getMessageType().getCode());
            messageDo.setSid("000000000000");
            messageDo.setLife(-1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            messageDo.setTs(sdf.format(now));
            messageDo.setMsg(jsonMsg);
            //create SQ
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minit = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            Long sq = hour*3600L+minit*60L+second;
            messageDo.setSq(sq);
            messageDo.Encrypt(terminalDo.getKeyt());
            ChannelHandlerContext ctx = CommonConstant.udp_link.get(terminalDo.getId());
            if(ctx != null){
                DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer(mapper.writeValueAsString(messageDo).getBytes()),new InetSocketAddress(terminalDo.getIp(),terminalDo.getPort()));
                ctx.writeAndFlush(dp);
                commandDo.setStatus(1);
                commandService.modify(commandDo);
                ResultDo resultDo = new ResultDo();
                DataDo data = new DataDo();
                data.setId(switchParam.getData());
                resultDo.setData(data);
                resultDo.setValue(Double.parseDouble(switchParam.getSwitcher().toString()));
                resultService.updateRealTime(resultDo);
            }else{
                commandDo.setStatus(0);
                commandService.modify(commandDo);
                return BizResult.error("设备离线！");
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return BizResult.error(e.getMessage());
        }
        return BizResult.success();
    }

    @Override
    public BizResult downdata(DowndataParam switchParam) {
        try {
            TerminalDo terminalDo = findById(switchParam.getTerminal());
            MessageTypeDo messageTypeDo = (MessageTypeDo) messageTypeDoMapper.findByCode("2013");
            Date now = new Date();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            //make command
            CommandDo commandDo = new CommandDo();
            commandDo.setMessageType(messageTypeDo);
            commandDo.setTerminal(terminalDo);
            commandDo.setTime(now);
            commandDo.setStatus(0);
            commandService.add(commandDo);
            Map<String,Object> msgMap = new HashMap<>();
            msgMap.put("cmd_id",commandDo.getId());
            msgMap.put("rcds",switchParam.getRcds());
            String jsonMsg = mapper.writeValueAsString(msgMap);
            commandDo.setJsonMsg(jsonMsg);
            MessageDo messageDo = new MessageDo();
            messageDo.setCv(Double.parseDouble(systemConfigService.findByCode("cv").getValue()));
            messageDo.setSv(Double.parseDouble(systemConfigService.findByCode("sv").getValue()));
            messageDo.setMid(commandDo.getMessageType().getCode());
            messageDo.setSid("000000000000");
            messageDo.setLife(-1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            messageDo.setTs(sdf.format(now));
            messageDo.setMsg(jsonMsg);
            //create SQ
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minit = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            Long sq = hour*3600L+minit*60L+second;
            messageDo.setSq(sq);
            messageDo.Encrypt(terminalDo.getKeyt());
            ChannelHandlerContext ctx = CommonConstant.udp_link.get(terminalDo.getId());
            if(ctx != null){
                DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer(mapper.writeValueAsString(messageDo).getBytes()),new InetSocketAddress(terminalDo.getIp(),terminalDo.getPort()));
                ctx.writeAndFlush(dp);
                commandDo.setStatus(1);
                commandService.modify(commandDo);
            }else{
                commandDo.setStatus(0);
                commandService.modify(commandDo);
                return BizResult.error("设备离线！");
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return BizResult.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return BizResult.error(e.getMessage());
        }
        return BizResult.success();
    }

    @Override
    public BizResult lstdata(TerminalDo terminalDo) {
        return BizResult.success(dataDoMapper.lstbyterminal(terminalDo.getId()));
    }


    public static void main(String[] args){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minit = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println("H:"+hour+",M:"+minit+",S:"+second);

    }
}
