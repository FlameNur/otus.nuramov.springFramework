package com.nuramov.hw02Questionnaire.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.nuramov.hw02Questionnaire")
@PropertySource("classpath:resources.properties")
public class AppConfig {
}
