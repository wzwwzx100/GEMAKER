package com.mogu.GEMAKER.api.action;

import com.mogu.GEMAKER.model.entity.*;
import com.mogu.GEMAKER.model.params.TemplatePara;
import com.mogu.GEMAKER.service.BaseTypeService;
import com.mogu.GEMAKER.service.MarketService;
import com.mogu.GEMAKER.service.TemplateService;
import com.mogu.GEMAKER.util.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.BaseType;

import javax.annotation.Resource;

@RestController
public class SettingCtrl extends  BaseCtrl{



    @Autowired
    private BaseTypeService messageTypeService;


    @Autowired
    private BaseTypeService deviceTypeService;

    @Autowired
    private BaseTypeService dataTypeService;

    @Autowired
    private BaseTypeService sensorTypeService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private TemplateService templateService;


    /**
     * 注册消息类型
     * @return
     */
    @RequestMapping(value = "setting/messagetype/register",method = RequestMethod.POST)
    public WebResult registerMessage(@RequestBody MessageTypeDo messageTypeDo){
        return  new WebResult(messageTypeService.register(messageTypeDo));
    }


    /**
     * 获取消息类型
     * @return
     */
    @RequestMapping(value = "setting/messagetype/get",method = RequestMethod.POST)
    public WebResult getMessage(@RequestBody MessageTypeDo messageTypeDo){
        return  new WebResult(messageTypeService.get(messageTypeDo));
    }


    /**
     * 禁用消息类型
     * @return
     */
    @RequestMapping(value = "setting/messagetype/disable",method = RequestMethod.POST)
    public WebResult disableMessage(@RequestBody MessageTypeDo messageTypeDo){
        messageTypeDo.setValid(0);
        return  new WebResult(messageTypeService.modify(messageTypeDo));
    }

    /**
     * 启用消息类型
     * @return
     */
    @RequestMapping(value = "setting/messagetype/enable",method = RequestMethod.POST)
    public WebResult enableMessage(@RequestBody MessageTypeDo messageTypeDo){
        messageTypeDo.setValid(1);
        return  new WebResult(messageTypeService.modify(messageTypeDo));
    }

    /**
     * 修改消息类型
     * @return
     */
    @RequestMapping(value = "setting/messagetype/modify",method = RequestMethod.POST)
    public WebResult modifyMessage(@RequestBody MessageTypeDo messageTypeDo){
        return  new WebResult(messageTypeService.modify(messageTypeDo));
    }

    /**
     * 消息类型列表
     * @return
     */
    @RequestMapping(value = "setting/messagetype/lst",method = RequestMethod.POST)
    public WebResult lstMessgae(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize,@RequestParam(value = "direction",required = false) Integer direction,@RequestParam(value = "code",required = false) String code){
        MessageTypeDo messageTypeDo = new MessageTypeDo();
        if(direction != null && direction != 0){
            messageTypeDo.setDirection(direction);
        }
        if(code != null && code.length() > 0){
            messageTypeDo.setCode(code);
        }
        return  new WebResult(messageTypeService.lst(pageNum,pageSize,messageTypeDo));
    }


    /**
     * 注册设备类型
     * @return
     */
    @RequestMapping(value = "setting/devicetype/register",method = RequestMethod.POST)
    public WebResult registerDevice(@RequestBody DeviceTypeDo deviceTypeDo){
        return  new WebResult(deviceTypeService.register(deviceTypeDo));
    }

    /**
     * 获取设备类型
     * @return
     */
    @RequestMapping(value = "setting/devicetype/get",method = RequestMethod.POST)
    public WebResult getDevice(@RequestBody DeviceTypeDo deviceTypeDo){
        return  new WebResult(deviceTypeService.get(deviceTypeDo));
    }



    /**
     * 删除设备类型
     * @return
     */
    @RequestMapping(value = "setting/devicetype/del",method = RequestMethod.POST)
    public WebResult deleteDevice(@RequestBody DeviceTypeDo deviceTypeDo){
        return  new WebResult(deviceTypeService.delete(deviceTypeDo.getId()));
    }


    /**
     * 修改设备类型
     * @return
     */
    @RequestMapping(value = "setting/devicetype/modify",method = RequestMethod.POST)
    public WebResult modifyDevice(@RequestBody DeviceTypeDo deviceTypeDo){
        return  new WebResult(deviceTypeService.modify(deviceTypeDo));
    }



    /**
     * 设备类型列表
     * @return
     */
    @RequestMapping(value = "setting/devicetype/lst",method = RequestMethod.POST)
    public WebResult lstDevice(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize,@RequestParam(value = "text",required = false) String text){
        BaseTypeDo datatypedo = new DeviceTypeDo();
        if(!StringUtils.isEmpty(text)){
            datatypedo.setCode("%"+text+"%");
        }
        return  new WebResult(deviceTypeService.lst(pageNum,pageSize,datatypedo));
    }

    /**
     * 注册数据类型
     * @return
     */
    @RequestMapping(value = "setting/datatype/register",method = RequestMethod.POST)
    public WebResult datatypeRegister(@RequestBody DataTypeDo dataTypeDo){
        return  new WebResult(dataTypeService.register(dataTypeDo));
    }

    /**
     * 获取数据类型
     * @return
     */
    @RequestMapping(value = "setting/datatype/get",method = RequestMethod.POST)
    public WebResult getDatatype(@RequestBody DataTypeDo dataTypeDo){
        return  new WebResult(dataTypeService.get(dataTypeDo));
    }

    /**
     * 删除数据类型
     * @return
     */
    @RequestMapping(value = "setting/datatype/del",method = RequestMethod.POST)
    public WebResult datatypeDelete(@RequestBody DataTypeDo dataTypeDo){
        return  new WebResult(dataTypeService.delete(dataTypeDo.getId()));
    }

    /**
     * 修改数据类型
     * @return
     */
    @RequestMapping(value = "setting/datatype/modify",method = RequestMethod.POST)
    public WebResult datatypeModify(@RequestBody DataTypeDo dataTypeDo){
        return  new WebResult(dataTypeService.modify(dataTypeDo));
    }

    /**
     * 数据类型列表
     * @return
     */
    @RequestMapping(value = "setting/datatype/lst",method = RequestMethod.POST)
    public WebResult lstData(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize,@RequestParam(value = "text",required = false) String text){
        BaseTypeDo datatypedo = new DataTypeDo();
        if(!StringUtils.isEmpty(text)){
            datatypedo.setCode("%"+text+"%");
        }
        return  new WebResult(dataTypeService.lst(pageNum,pageSize,datatypedo));
    }

    /**
     * 注册传感器类型
     * @return
     */
    @RequestMapping(value = "setting/sensortype/register",method = RequestMethod.POST)
    public WebResult registerSensorType(@RequestBody SensorTypeDo sensorTypeDo){
        return  new WebResult(sensorTypeService.register(sensorTypeDo));
    }

    /**
     * 删除传感器类型
     * @return
     */
    @RequestMapping(value = "setting/sensortype/del",method = RequestMethod.POST)
    public WebResult deleteSensorType(@RequestBody SensorTypeDo sensorTypeDo){
        return  new WebResult(sensorTypeService.delete(sensorTypeDo.getId()));
    }

    /**
     * 修改传感器类型
     * @return
     */
    @RequestMapping(value = "setting/sensortype/modify",method = RequestMethod.POST)
    public WebResult modifySensorType(@RequestBody SensorTypeDo sensorTypeDo){
        return  new WebResult(sensorTypeService.modify(sensorTypeDo));
    }

    /**
     * 获取传感器类型
     * @return
     */
    @RequestMapping(value = "setting/sensortype/get",method = RequestMethod.POST)
    public WebResult getSensorType(@RequestBody SensorTypeDo sensorTypeDo){
        return  new WebResult(sensorTypeService.get(sensorTypeDo));
    }

    /**
     * 传感器类型列表
     * @return
     */
    @RequestMapping(value = "setting/sensortype/lst",method = RequestMethod.POST)
    public WebResult lstSensor(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize ,@RequestParam(value = "text",required = false) String text){
        BaseTypeDo sensorTypeDo = new SensorTypeDo();
        if(!StringUtils.isEmpty(text)){
            sensorTypeDo.setCode("%"+text+"%");
        }
        return  new WebResult(sensorTypeService.lst(pageNum,pageSize,sensorTypeDo));
    }

    /**
     * 注册产品
     * @return
     */
    @RequestMapping(value = "setting/product/register",method = RequestMethod.POST)
    public WebResult registerProduct(@RequestBody ProductDo productDo){
        return  new WebResult(marketService.registerProduct(productDo));
    }

    /**
     * 获取产品
     * @return
     */
    @RequestMapping(value = "setting/product/get",method = RequestMethod.POST)
    public WebResult getProduct(@RequestBody ProductDo productDo){
        return  new WebResult(marketService.getProduct(productDo));
    }


    /**
     * 修改产品
     * @return
     */
    @RequestMapping(value = "setting/product/modify",method = RequestMethod.POST)
    public WebResult modifyProduct(@RequestBody ProductDo productDo){
        return  new WebResult(marketService.modifyProduct(productDo));
    }

    /**
     * 产品列表
     * @return
     */
    @RequestMapping(value = "setting/product/lst",method = RequestMethod.POST)
    public WebResult lstProduct(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return  new WebResult(marketService.lstProduct(pageNum,pageSize));
    }
    /**
     * 删除产品
     * @return
     */
    @RequestMapping(value = "setting/product/del",method = RequestMethod.POST)
    public WebResult delProduct(@RequestBody ProductDo productDo){
        return  new WebResult(marketService.deleteProduct(productDo));
    }

    /**
     * 注册应用
     * @return
     */
    @RequestMapping(value = "setting/app/register",method = RequestMethod.POST)
    public WebResult registerApp(@RequestBody ApplicationDo applicationDo){
        return  new WebResult(marketService.registerApplication(applicationDo));
    }


    /**
     * 获取应用
     * @return
     */
    @RequestMapping(value = "setting/app/get",method = RequestMethod.POST)
    public WebResult getApp(@RequestBody ApplicationDo applicationDo){
        return  new WebResult(marketService.getApplication(applicationDo));
    }

    /**
     * 应用列表
     * @return
     */
    @RequestMapping(value = "setting/app/lst",method = RequestMethod.POST)
    public WebResult lstApp(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return  new WebResult(marketService.lstApplication(pageNum,pageSize));
    }

    /**
     * 禁用应用
     * @return
     */
    @RequestMapping(value = "setting/app/disable",method = RequestMethod.POST)
    public WebResult disableApp(@RequestBody ApplicationDo applicationDo){
        return  new WebResult(marketService.disableApplication(applicationDo));
    }

    /**
     * 启用应用
     * @return
     */
    @RequestMapping(value = "setting/app/enable",method = RequestMethod.POST)
    public WebResult enableApp(@RequestBody ApplicationDo applicationDo){
        return  new WebResult(marketService.enableApplication(applicationDo));
    }

    /**
     * 修改应用
     * @return
     */
    @RequestMapping(value = "setting/app/modify",method = RequestMethod.POST)
    public WebResult modifyApp(@RequestBody ApplicationDo applicationDo){
        return  new WebResult(marketService.modifyApplication(applicationDo));
    }


    /**
     * 模板列表
     * @return
     */
    @RequestMapping(value = "setting/template/lst",method = RequestMethod.POST)
    public WebResult lstTemplate(@RequestParam(value = "pageNum",required = false) Integer pageNum, @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return  new WebResult(templateService.lst(pageNum,pageSize));
    }

    /**
     * 增加模板
     * @return
     */
    @RequestMapping(value = "setting/template/add",method = RequestMethod.POST)
    public WebResult addTemplate(@RequestBody TemplateDo templateDo){
        return  new WebResult(templateService.add(templateDo));
    }


    /**
     * 获取模板
     * @return
     */
    @RequestMapping(value = "setting/template/get",method = RequestMethod.POST)
    public WebResult getTemplate(@RequestBody TemplateDo templateDo){
        return  new WebResult(templateService.get(templateDo));
    }


    /**
     * 修改应用
     * @return
     */
    @RequestMapping(value = "setting/template/modify",method = RequestMethod.POST)
    public WebResult modifyTemplate(@RequestBody TemplateDo templateDo){
        return  new WebResult(templateService.modify(templateDo));
    }


    /**
     * 删除模板
     * @return
     */
    @RequestMapping(value = "setting/template/del",method = RequestMethod.POST)
    public WebResult delTemplate(@RequestBody TemplateDo templateDo){
        return  new WebResult(templateService.del(templateDo));
    }

}
