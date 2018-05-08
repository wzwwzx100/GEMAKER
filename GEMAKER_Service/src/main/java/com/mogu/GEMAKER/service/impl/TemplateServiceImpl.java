package com.mogu.GEMAKER.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.dao.mapper.TemplateDoMapper;
import com.mogu.GEMAKER.model.entity.TemplateDo;
import com.mogu.GEMAKER.service.TemplateService;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDoMapper templateDoMapper;


    @Override
    public BizResult add(TemplateDo templateDo) {
        return BizResult.success(templateDoMapper.add(templateDo));
    }

    @Override
    public BizResult del(TemplateDo templateDo) {
        return BizResult.success(templateDoMapper.del(templateDo));
    }

    @Override
    public BizResult modify(TemplateDo templateDo) {
        return BizResult.success(templateDoMapper.modify(templateDo));
    }

    @Override
    public BizResult get(TemplateDo templateDo) {
        return BizResult.success(templateDoMapper.get(templateDo));
    }

    @Override
    public BizResult lst(Integer pageSize, Integer pageNum) {
        if(pageNum == null || pageSize == null){
            List<TemplateDo> lst = templateDoMapper.lst();
            return BizResult.success(lst);
        }
        String orderBy = "id asc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<TemplateDo> lst = templateDoMapper.lst();
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }


}
