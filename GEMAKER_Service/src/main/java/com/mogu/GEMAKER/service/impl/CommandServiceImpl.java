package com.mogu.GEMAKER.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.constants.CommonConstant;
import com.mogu.GEMAKER.dao.mapper.CommandDoMapper;
import com.mogu.GEMAKER.dao.mapper.SensorDoMapper;
import com.mogu.GEMAKER.dao.mapper.TemplateDoMapper;
import com.mogu.GEMAKER.model.entity.*;
import com.mogu.GEMAKER.service.CommandService;
import com.mogu.GEMAKER.service.MessageService;
import com.mogu.GEMAKER.service.SystemConfigService;
import com.mogu.GEMAKER.service.TemplateService;
import com.mogu.GEMAKER.service.net.UDPClient;
import com.mogu.GEMAKER.util.BizResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommandServiceImpl implements CommandService{
    private static final Logger log = LoggerFactory.getLogger(TerminalServiceImpl.class);


    @Autowired
    private CommandDoMapper commandDoMapper;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TemplateDoMapper templateService;


    @Override
    public BizResult add(CommandDo commandDo) {
        int ret = -1;
        ret = commandDoMapper.add(commandDo);
        return ret == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult modify(CommandDo commandDo) {
        int ret = -1;
        ret = commandDoMapper.modify(commandDo);
        return ret == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult success(Long id) {
        int ret = -1;
        ret = commandDoMapper.success(id);
        return ret == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult del(CommandDo commandDo) {
        return commandDoMapper.del(commandDo.getId()) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult send(CommandDo commandDo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Calendar c = Calendar.getInstance();
            long sq = c.get(Calendar.HOUR_OF_DAY)*3600+c.get(Calendar.MINUTE)*60+c.get(Calendar.SECOND);
            // step 1 : 组装message信息
            MessageDo messageDo = new MessageDo();
            messageDo.setCv(Double.parseDouble(systemConfigService.findByCode("cv").getValue()));
            messageDo.setSv(Double.parseDouble(systemConfigService.findByCode("sv").getValue()));
            messageDo.setSq(sq);
            messageDo.setMid(commandDo.getMessageType().getCode());
            messageDo.setSid("000000000000");
            messageDo.setLife(-1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            messageDo.setTs(sdf.format(commandDo.getTime()));
            messageDo.Encrypt(systemConfigService.findByCode("keyt").getValue());
            Map<String,Object> msgMap = new HashMap<>();
            msgMap.put("cmd_id",commandDo.getId());
            msgMap.put("cmd",commandDo.getJsonMsg());
            String jsonMsg = mapper.writeValueAsString(msgMap);
            messageDo.setMsg(jsonMsg);
            String jsonText = mapper.writeValueAsString(messageDo);
            //step 2 保存交易记录
            MsgInfoDo msgInfoDo = new MsgInfoDo();
            msgInfoDo.setTerminalId(commandDo.getTerminal().getId());
            msgInfoDo.setLife(-1);
            msgInfoDo.setSq(sq);
            msgInfoDo.setAtTime(commandDo.getTime());
            msgInfoDo.setMsg(jsonMsg);
            msgInfoDo.setPrivateMsg(jsonMsg);
            msgInfoDo.setMsgCode("1143");
            msgInfoDo.setMsgCodeName("下发配置");
            msgInfoDo.setMsgType(1);
            msgInfoDo.setJsonText(jsonText);
            messageService.addMessage(msgInfoDo);
            // step 3 发送消息
            log.info("start sending command :"+commandDo.getId());
            ChannelHandlerContext ctx = CommonConstant.udp_link.get(msgInfoDo.getTerminalId());
            if(ctx != null){
                DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer(mapper.writeValueAsString(messageDo).getBytes()),new InetSocketAddress(commandDo.getTerminal().getIp(),commandDo.getTerminal().getPort()));
                ctx.writeAndFlush(dp);
                commandDo.setStatus(1);
                modify(commandDo);
            }else{
                return BizResult.error("设备离线");
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
    public MessageDo createMessage(CommandDo commandDo) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Calendar c = Calendar.getInstance();
        MessageDo messageDo = new MessageDo();
        messageDo.setCv(Double.parseDouble(systemConfigService.findByCode("cv").getValue()));
        messageDo.setSv(Double.parseDouble(systemConfigService.findByCode("sv").getValue()));
        messageDo.setMid(commandDo.getMessageType().getCode());
        messageDo.setSid("000000000000");
        messageDo.setLife(-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        messageDo.setTs(sdf.format(commandDo.getTime()));
        Map<String,Object> msgMap = new HashMap<>();

        if(commandDo.getMessageType().getCode().equals("2013")){
            messageDo.setMsg(commandDo.getJsonMsg());
        }else {
            msgMap.put("cmd_id",commandDo.getId());
            msgMap.put("cmd", commandDo.getJsonMsg());
            String jsonMsg = mapper.writeValueAsString(msgMap);
            messageDo.setMsg(jsonMsg);
        }
        return messageDo;
    }

    @Override
    public BizResult lst(int pageNum, int pageSize, CommandDo commandDo) {
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<CommandDo> lst = commandDoMapper.lst(commandDo);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }

    @Override
    public List<CommandDo> findFailed(TerminalDo terminalId) {
        return commandDoMapper.findFailed(terminalId);
    }

    @Override
    public BizResult readTemplate(TemplateDo templateDo) {
        //read template
        templateDo = templateService.get(templateDo);
        String teminalId= templateDo.getParam1();
        TerminalDo terminalDo = new TerminalDo();
        terminalDo.setId(teminalId);
        //create command
        CommandDo cmd = new CommandDo();
        cmd.setStatus(0);
        cmd.setMessageType(templateDo.getMessageType());
        cmd.setTerminal(terminalDo);



        return null;
    }
}
