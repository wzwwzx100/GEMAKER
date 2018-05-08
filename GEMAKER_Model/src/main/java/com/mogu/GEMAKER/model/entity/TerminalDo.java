package com.mogu.GEMAKER.model.entity;

import java.util.Date;

public class TerminalDo {
    private String id;
    private ProductDo product;
    private ApplicationDo application;
    private Date lastTime;
    private String ip;
    private int port;
    private String keyt;

    private String belong;

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDo getProduct() {
        return product;
    }

    public void setProduct(ProductDo product) {
        this.product = product;
    }

    public ApplicationDo getApplication() {
        return application;
    }

    public void setApplication(ApplicationDo application) {
        this.application = application;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
