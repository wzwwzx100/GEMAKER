package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.entity.TemplateDo;
import com.mogu.GEMAKER.util.BizResult;

public interface TemplateService {

    BizResult add(TemplateDo templateDo);
    BizResult del(TemplateDo templateDo);
    BizResult modify(TemplateDo templateDo);
    BizResult get(TemplateDo templateDo);
    BizResult lst(Integer pageSize,Integer pageNum);
}
