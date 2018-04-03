package com.mogu.GEMAKER.api.action;

import com.mogu.GEMAKER.entity.*;
import com.mogu.GEMAKER.service.MessageService;
import com.mogu.GEMAKER.service.ResultService;
import com.mogu.GEMAKER.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultCtrl extends  BaseCtrl {



    @Autowired
    private ResultService resultService;

    @Autowired
    private MessageService messageService;


    /**
     * 数据列表
     * @return
     */
    @RequestMapping(value = "result/lst",method = RequestMethod.POST)
    public WebResult datalst(@RequestBody ResultParam result){
        return new WebResult(resultService.lst(result.getPageNum(),result.getPageSize(),result));
    }


    /**
     * 交易消息列表
     * @return
     */
    @RequestMapping(value = "message/lst",method = RequestMethod.POST)
    public WebResult msglst(@RequestBody MessageParam msg){
        return new WebResult(messageService.lst(msg));
    }


    /**
     * 实时数据
     * @param terminalDo
     * @return
     */
    @RequestMapping(value = "result/realValue",method = RequestMethod.POST)
    public WebResult realValue(@RequestBody TerminalDo terminalDo){
        return  new WebResult(resultService.realValue(terminalDo));
    }


}
