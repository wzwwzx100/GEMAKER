package com.mogu.GEMAKER.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.Enum.ResultCode;
import com.mogu.GEMAKER.dao.mapper.DataTypeDoMapper;
import com.mogu.GEMAKER.model.entity.BaseTypeDo;
import com.mogu.GEMAKER.service.BaseTypeService;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dataTypeService")
public class DataTypeServiceImpl implements BaseTypeService {



    @Autowired
    private DataTypeDoMapper dataTypeDoMapper;

    @Override
    public BizResult get(BaseTypeDo baseTypeDo) {
        return BizResult.success(dataTypeDoMapper.findById(baseTypeDo));
    }

    @Override
    public BizResult register(BaseTypeDo baseTypeDo) {
        BaseTypeDo exist = dataTypeDoMapper.findByCode(baseTypeDo.getCode());
        if(exist != null){
            return BizResult.error(ResultCode.CODE_EXIST);
        }
        baseTypeDo.setClazz(2);
        return dataTypeDoMapper.register(baseTypeDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult delete(Integer id) {
        return dataTypeDoMapper.delete(id) == 1 ? BizResult.success():BizResult.error();
    }

    @Override
    public BizResult modify(BaseTypeDo baseTypeDo) {
        return dataTypeDoMapper.modify(baseTypeDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult lst(Integer pageNum, Integer pageSize,BaseTypeDo baseTypeDo) {
        if(pageNum == null | pageSize == null){
            return BizResult.success(dataTypeDoMapper.lst(null));
        }
        String orderBy = "id asc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<BaseTypeDo> lst = dataTypeDoMapper.lst(baseTypeDo);
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }
}
