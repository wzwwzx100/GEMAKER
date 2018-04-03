package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.entity.SensorDo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SensorDoMapper {

    SensorDo findById(SensorDo sensorDo);

    int register(SensorDo sensorDo);

    List<SensorDo> lst(String terminal);

    int update(SensorDo sensorDo);

    int delete(SensorDo sensorDo);
}
