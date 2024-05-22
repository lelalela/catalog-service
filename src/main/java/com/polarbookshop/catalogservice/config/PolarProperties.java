package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar") //CatalogServiceApplication 에다가 설정해서 여기서 쓴다 application.yml에 작성 ex) polar.greeting
public class PolarProperties {

    private String greeting;


    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
