package com.lilierygina.detailingstudio.entity;

public enum ServiceType {
    NONE("Нет"),
    CAR_WASH2("Двухфазная мойка автомобиля"),
    CAR_WASH3("Трехфазная мойка автомобиля"),
    CAR_WASH("Мойка автомобиля"),
    DRY_CLEANING("Химчистка салона"),
    GLUE_FIRM("Оклейка пленкой"),
    POLISHING_CAR_BODY("Полировка кузова"),
    POLISHING_CAR_HEADLIGHTS("Полировка фар")
    ;

    private String serviceType;
    ServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public static String getName(String st) {
        for (ServiceType i : ServiceType.values()) {
            if (st.equals(i.name())) {
                return i.serviceType;
            }
        }
        return null;
    }
}
