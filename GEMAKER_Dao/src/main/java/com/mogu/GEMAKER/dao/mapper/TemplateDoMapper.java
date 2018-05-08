package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.TemplateDo;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TemplateDoMapper {

    int add(TemplateDo templateDo);

    int del(TemplateDo templateDo);

    TemplateDo get(TemplateDo templateDo);

    int modify(TemplateDo templateDo);

    List<TemplateDo> lst();
}
