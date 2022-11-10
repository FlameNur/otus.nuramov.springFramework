package com.nuramov.hw02Questionnaire.messageSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessagePrinterImpl implements MessagePrinter {
    // Обработчик сообщений, предназначенный для локализации сообщений
    private MessageSource messageSource;

    @Autowired
    public MessagePrinterImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void printMessage(String messageKey) {
        // Считываем ключ из messages_ru.properties - message и локализуем значение (сообщение)
        System.out.print(messageSource.getMessage(messageKey, new String[] {}, Locale.getDefault()));
    }
}
