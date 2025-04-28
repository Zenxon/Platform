package com.k2124884.Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PlatformApplication {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        initializeContext(args);
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void initializeContext(String[] args) {
        if (applicationContext == null) {
            applicationContext = SpringApplication.run(PlatformApplication.class, args);
        }
    }

    public static void closeContext() {
        if (applicationContext != null) {
            applicationContext.close();
            applicationContext = null;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}