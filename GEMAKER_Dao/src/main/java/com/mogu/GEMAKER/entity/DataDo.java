package com.mogu.GEMAKER.entity;

import java.util.Date;

public class DataDo {
    private Long id;
    private Long sensor;
    private DeviceTypeDo deviceType;
    private DataTypeDo dataType;
    private Date lastTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensor() {
        return sensor;
    }

    public void setSensor(Long sensor) {
        this.sensor = sensor;
    }

    public DeviceTypeDo getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypeDo deviceType) {
        this.deviceType = deviceType;
    }

    public DataTypeDo getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeDo dataType) {
        this.dataType = dataType;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
