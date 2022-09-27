package com.nuramov.hw02Questionnaire.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan("com.nuramov.hw02Questionnaire")
@PropertySource("classpath:resources.properties")
public class AppConfig {

    @Bean
    /**
     * Используется для обработки/локализации выводимых сообщений
     */
    public MessageSource messageSource () {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        // "classpath:messages/messages..." прописано общее начало наименования файлов, если их несколько
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
