package com.mogu.GEMAKER.service.impl;

import com.mogu.GEMAKER.dao.mapper.DataDoMapper;
import com.mogu.GEMAKER.dao.mapper.SensorDoMapper;
import com.mogu.GEMAKER.entity.DataDo;
import com.mogu.GEMAKER.service.DataService;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDoMapper dataDoMapper;

    @Override
    public BizResult register(DataDo dataDo) {
        int ret = dataDoMapper.register(dataDo);
        return ret == 1? BizResult.success() : BizResult.error("注册失败！");
    }

    @Override
    public BizResult delete(DataDo dataDo) {
        int ret = dataDoMapper.delete(dataDo);
        return ret == 1? BizResult.success() : BizResult.error("删除失败！");
    }

    @Override
    public BizResult modify(DataDo dataDo) {
        int ret = dataDoMapper.update(dataDo);
        return ret == 1? BizResult.success() : BizResult.error("修改失败！");
    }

    @Override
    public BizResult lst(Long sensor) {
        List<DataDo> lst = dataDoMapper.lst(sensor);
        return BizResult.success(lst);
    }

    @Override
    public BizResult findById(DataDo dataDo) {
        return BizResult.success(dataDoMapper.findById(dataDo.getId()));
    }
}
