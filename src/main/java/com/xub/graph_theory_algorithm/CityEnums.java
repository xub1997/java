package com.xub.graph_theory_algorithm;

public enum CityEnums {
    GZ(0,"广州"),
    SZ(1,"深圳"),
    MZ(2,"梅州"),
    FS(3,"佛山"),
    MM(4,"茂名"),
    UNKNOWN(-1,"未知");
    private int code;
    private String cityName;

    CityEnums(int code, String cityName) {
        this.code = code;
        this.cityName = cityName;
    }

    public static String getByCode(int code){
        CityEnums[] values = CityEnums.values();
        for (CityEnums value : values) {
            if(value.code==code){
                return value.cityName;
            }
        }
        return CityEnums.UNKNOWN.cityName;
    }
}
