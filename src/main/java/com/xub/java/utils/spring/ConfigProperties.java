package com.xub.java.utils.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置
 */
@Data
@ConfigurationProperties(prefix = "system")
@Component
public class ConfigProperties {
    private Map<String, String> config = new HashMap<>();
}
