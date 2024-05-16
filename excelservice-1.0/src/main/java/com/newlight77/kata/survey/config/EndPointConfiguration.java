package com.newlight77.kata.survey.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "endpoint")
public class EndPointConfiguration {
    private String survey;
    private String campaign;

}
