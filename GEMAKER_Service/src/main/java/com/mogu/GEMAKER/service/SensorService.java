package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.model.entity.SensorDo;
import com.mogu.GEMAKER.util.BizResult;

public interface SensorService {
    BizResult findById(SensorDo sensorDo);

    BizResult register(SensorDo sensorDo);

    BizResult modify(SensorDo sensorDo);

    BizResult delete(SensorDo sensorDo);

    BizResult lst(String terminal);
}
