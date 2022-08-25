package com.lilierygina.detailingstudio.entity;

public enum ServiceType {
    NONE("Нет"),
    CAR_WASH("Мойка автомобиля"),
    DRY_CLEANING("Химчистка салона"),
    GLUE_FIRM("Оклейка пленкой"),
    POLISHING("Полировка")
    ;

    private String serviceType;
    ServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }
}
