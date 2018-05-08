package com.mogu.GEMAKER.api.action;

import com.mogu.GEMAKER.model.entity.DataDo;
import com.mogu.GEMAKER.model.entity.SensorDo;
import com.mogu.GEMAKER.model.entity.TerminalDo;
import com.mogu.GEMAKER.model.params.DowndataParam;
import com.mogu.GEMAKER.model.params.SwitchParam;
import com.mogu.GEMAKER.model.params.TerminalParam;
import com.mogu.GEMAKER.service.DataService;
import com.mogu.GEMAKER.service.ResultService;
import com.mogu.GEMAKER.service.SensorService;
import com.mogu.GEMAKER.service.TerminalService;
import com.mogu.GEMAKER.util.BizResult;
import com.mogu.GEMAKER.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceCtrl extends  BaseCtrl{


    @Autowired
    private TerminalService terminalService;


    @Autowired
    private SensorService sensorService;


    @Autowired
    private DataService dataService;

    @Autowired
    private ResultService resultService;


    /**
     * 注册终端
     * @return
     */
    @RequestMapping(value = "device/register",method = RequestMethod.POST)
    public WebResult register(@RequestBody TerminalDo terminal){
        return  new WebResult(terminalService.register(terminal));
    }

    /**
     * 查询终端
     * @return
     */
    @RequestMapping(value = "device/get",method = RequestMethod.POST)
    public WebResult terminalGet(@RequestBody TerminalDo terminal){
        return  new WebResult(BizResult.success(terminalService.findById(terminal.getId())));
    }


    /**
     * 终端修改
     * @return
     */
    @RequestMapping(value = "device/modify",method = RequestMethod.POST)
    public WebResult deviceModify(@RequestBody TerminalDo terminal){
        return  new WebResult(terminalService.modify(terminal));
    }


    /**
     * 终端列表
     * @return
     */
    @RequestMapping(value = "device/lst",method = RequestMethod.POST)
    public WebResult lst(@RequestBody TerminalParam terminalParam){
        return new WebResult(terminalService.lst(terminalParam.getPageNum(),terminalParam.getPageSize(),terminalParam));
    }

    /**
     * 删除终端
     * @return
     */
    @RequestMapping(value = "device/del",method = RequestMethod.POST)
    public WebResult del(@RequestBody TerminalDo terminal){
        return new WebResult(terminalService.delete(terminal));
    }


    /**
     * 离线列表
     * @return
     */
    @RequestMapping(value = "device/off_line",method = RequestMethod.POST)
    public WebResult offLine(@RequestBody TerminalParam terminalParam){
        return new WebResult(terminalService.offLine(terminalParam.getPageNum(),terminalParam.getPageSize(),terminalParam));
    }


    /**
     * 注册传感器
     * @return
     */
    @RequestMapping(value = "sensor/register",method = RequestMethod.POST)
    public WebResult registerSensor(@RequestBody SensorDo sensorDo){
        return  new WebResult(sensorService.register(sensorDo));
    }

    /**
     * 删除传感器
     * @return
     */
    @RequestMapping(value = "sensor/del",method = RequestMethod.POST)
    public WebResult sensorDel(@RequestBody SensorDo sensorDo){
        return  new WebResult(sensorService.delete(sensorDo));
    }

    /**
     * 查询传感器
     * @return
     */
    @RequestMapping(value = "sensor/get",method = RequestMethod.POST)
    public WebResult sensorGet(@RequestBody SensorDo sensorDo){
        return  new WebResult(sensorService.findById(sensorDo));
    }


    /**
     * 修改传感器
     * @return
     */
    @RequestMapping(value = "sensor/modify",method = RequestMethod.POST)
    public WebResult sensorModify(@RequestBody SensorDo sensorDo){
        return  new WebResult(sensorService.modify(sensorDo));
    }


    /**
     * 传感器列表
     * @return
     */
    @RequestMapping(value = "sensor/lst",method = RequestMethod.POST)
    public WebResult sensorlst(@RequestBody SensorDo sensorDo){
        return new WebResult(sensorService.lst(sensorDo.getTerminal()));
    }


    /**
     * 注册数据
     * @return
     */
    @RequestMapping(value = "data/register",method = RequestMethod.POST)
    public WebResult dataRegister(@RequestBody DataDo dataDo){
        return  new WebResult(dataService.register(dataDo));
    }


    /**
     * 查询数据
     * @return
     */
    @RequestMapping(value = "data/get",method = RequestMethod.POST)
    public WebResult dataGet(@RequestBody DataDo dataDo){
        return  new WebResult(dataService.findById(dataDo));
    }



    /**
     * 删除数据
     * @return
     */
    @RequestMapping(value = "data/del",method = RequestMethod.POST)
    public WebResult dataDel(@RequestBody DataDo dataDo){
        return  new WebResult(dataService.delete(dataDo));
    }

    /**
     *  修改数据
     * @return
     */
    @RequestMapping(value = "data/modify",method = RequestMethod.POST)
    public WebResult dataModify(@RequestBody DataDo dataDo){
        return  new WebResult(dataService.modify(dataDo));
    }


    /**
     * 数据列表
     * @return
     */
    @RequestMapping(value = "data/lst",method = RequestMethod.POST)
    public WebResult datalst(@RequestBody DataDo dataDo){
        return new WebResult(dataService.lst(dataDo.getSensor()));
    }


    /**
     * 下发配置
     * @return
     */
    @RequestMapping(value = "device/issueConfig",method = RequestMethod.POST)
    public WebResult issueConfig(@RequestBody TerminalDo terminalDo){
        return  new WebResult(terminalService.issueConfig(terminalDo));
    }

    /**
     * 下发开关信息
     * @return
     */
    @RequestMapping(value = "device/switch",method = RequestMethod.POST)
    public WebResult switcher(@RequestBody SwitchParam switchParam){
        return  new WebResult(terminalService.switcher(switchParam));
    }


    /**
     * 下发数据
     * @return
     */
    @RequestMapping(value = "device/downdata",method = RequestMethod.POST)
    public WebResult downdata(@RequestBody DowndataParam switchParam){
        return  new WebResult(terminalService.downdata(switchParam));
    }


    /**
     * 数据列表
     * @return
     */
    @RequestMapping(value = "device/lstdata",method = RequestMethod.POST)
    public WebResult lstdata(@RequestBody TerminalDo terminalDo){
        return  new WebResult(terminalService.lstdata(terminalDo));
    }

    /**
     * 下发
     * @return
     */
    @RequestMapping(value = "device/sendTest",method = RequestMethod.POST)
    public WebResult testSend(@RequestBody TerminalDo terminalDo){
        return  new WebResult(terminalService.testSend(terminalDo));
    }





}
