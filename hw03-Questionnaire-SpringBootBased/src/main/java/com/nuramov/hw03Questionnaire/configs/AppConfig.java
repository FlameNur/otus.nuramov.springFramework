package com.nuramov.hw03Questionnaire.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.util.Locale;
import java.util.Objects;

@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;

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

        // Задаем дефолтный язык из application.properties, чтобы выводить сообщения в зависимости от локализации/языка
        Locale.setDefault(Locale.forLanguageTag(Objects.requireNonNull(environment.getProperty("DefaultLocale"))));
        return messageSource;
    }
}
