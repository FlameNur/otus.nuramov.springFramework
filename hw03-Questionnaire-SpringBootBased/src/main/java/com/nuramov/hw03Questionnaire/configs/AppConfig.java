package com.nuramov.hw03Questionnaire.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

@Configuration
@ComponentScan("com.nuramov.hw03Questionnaire")
@PropertySource({"classpath:resources.properties", "classpath:configs/config.properties"})
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

        Locale.setDefault(getDefaultLocale());
        return messageSource;
    }

    /**
     * Метод getDefaultLocale возвращает Locale, заданный в config.properties,
     * чтобы выводить сообщения в зависимости от локализации/языка
     * @return - Locale, заданная в config.properties
     */
    private Locale getDefaultLocale() {
        Properties property = new Properties();
        Locale locale = Locale.ENGLISH;
        // Считываем из config.properties заданную Locale
        try(FileInputStream fileInputStream = new FileInputStream(
                "hw03-Questionnaire-SpringBootBased/src/main/resources/configs/config.properties")) {
            property.load(fileInputStream);
            locale = new Locale(property.getProperty("DefaultLocale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locale;
    }
}
