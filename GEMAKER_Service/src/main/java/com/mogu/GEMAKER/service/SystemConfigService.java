package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.common.SystemConfig;
import com.mogu.GEMAKER.util.BizResult;

public interface SystemConfigService {

    BizResult add(SystemConfig systemConfig);

    BizResult modify(SystemConfig systemConfig);

    BizResult lst();

    SystemConfig findByCode(String code);
}
