package com.hargeliya.configurations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class AppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

}
