package com.mogu.GEMAKER.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.Enum.ResultCode;
import com.mogu.GEMAKER.dao.mapper.DeviceTypeDoMapper;
import com.mogu.GEMAKER.model.entity.BaseTypeDo;
import com.mogu.GEMAKER.service.BaseTypeService;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.geom.Crossings;

import java.util.List;


@Service("deviceTypeService")
public class DeviceTypeServiceImpl implements BaseTypeService {


    @Autowired
    private DeviceTypeDoMapper deviceTypeDoMapper;


    @Override
    public BizResult get(BaseTypeDo baseTypeDo) {
        return BizResult.success(deviceTypeDoMapper.findById(baseTypeDo));
    }

    @Override
    public BizResult register(BaseTypeDo baseTypeDo) {
        BaseTypeDo exist = deviceTypeDoMapper.findByCode(baseTypeDo.getCode());
        if(exist != null){
            return BizResult.error(ResultCode.CODE_EXIST);
        }
        baseTypeDo.setClazz(3);
        return deviceTypeDoMapper.register(baseTypeDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult delete(Integer id) {
        return deviceTypeDoMapper.delete(id) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult modify(BaseTypeDo baseTypeDo) {
        return deviceTypeDoMapper.modify(baseTypeDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult lst(Integer pageNum, Integer pageSize,BaseTypeDo baseTypeDo) {
        if(pageNum == null || pageSize == null){
            return BizResult.success(deviceTypeDoMapper.lst(null));
        }
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<BaseTypeDo> lst = deviceTypeDoMapper.lst(baseTypeDo);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }
}
