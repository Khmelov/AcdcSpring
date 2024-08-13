package com.javarush.lesson15.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = LoggingRepositoryProperties.PREFIX)
public class LoggingRepositoryProperties {

    public static final String PREFIX = "logging.repository";
    /**
     * set log level TRACE, DEBUG, INFO, WARN, ERROR
     * default value - INFO
     */
    @Value("${" + PREFIX + ".level?:INFO}")
    String level;

    /**
     * set for enable ON, for disable OFF
     * default value - ON
     */
    @Value("${" + PREFIX + ".state?:ON}")
    String state;

}
