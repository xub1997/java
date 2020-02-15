package com.xub.java.utils.spring;


import lombok.NonNull;

public class ConfigUtil {

    private static ConfigProperties configProperties = SpringContextUtil.getBean(ConfigProperties.class);

    public static String getProperty(@NonNull String key){
        return configProperties.getConfig().get(key);
    }
}
