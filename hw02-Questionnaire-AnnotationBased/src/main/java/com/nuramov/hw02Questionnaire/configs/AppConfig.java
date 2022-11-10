package com.nuramov.hw02Questionnaire.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

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

        // Устанавливаем default Locale, чтобы выводить сообщения в зависимости от локализации/языка
        //Locale defaultLocale = Locale.ENGLISH;
        Locale defaultLocale = new Locale.Builder().setLanguage("ru").build();
        Locale.setDefault(defaultLocale);
        return messageSource;
    }
}
