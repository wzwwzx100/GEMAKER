package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.entity.TemplateDo;
import com.mogu.GEMAKER.util.BizResult;

public interface TemplateDoMapper {

    BizResult add(TemplateDo templateDo);

    BizResult del(TemplateDo templateDo);

    BizResult get(TemplateDo templateDo);

    BizResult modify(TemplateDo templateDo);

    BizResult lst();
}
