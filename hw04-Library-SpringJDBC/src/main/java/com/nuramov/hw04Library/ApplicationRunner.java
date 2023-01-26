package com.nuramov.hw04Library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@ConfigurationPropertiesScan("com.baeldung.configurationproperties")
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        //QuestionnaireLauncher questionnaireLauncher = context.getBean(QuestionnaireLauncher.class);
        //questionnaireLauncher.run();
    }

}
