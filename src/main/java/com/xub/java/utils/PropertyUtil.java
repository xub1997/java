package com.xub.java.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class PropertyUtil {

    private static Map<String, String> systemProperties = Maps.newConcurrentMap();

    private PropertyUtil() {
    }

    static {
        loadProperties();
    }

    /**
     * 加载配置文件
     */
    private static void loadProperties() {
        Properties properties = new Properties();
        try (InputStreamReader reader = new InputStreamReader(
                PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties"), StandardCharsets.UTF_8)) {
            properties.load(reader);
            Set<Object> keySet = properties.keySet();
            for (Object key : keySet) {
                systemProperties.put(String.valueOf(key), String.valueOf(properties.get(key)));
            }
        } catch (IOException e) {
            log.error("加载properties文件失败", e);
        }
    }

    public static String getProperty(String key) {
        return systemProperties.get(key);
    }

    public static void main(String[] args){
        System.out.println(systemProperties);
        System.out.println(getProperty("jdbc.user"));
    }

}
