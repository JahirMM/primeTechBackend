package com.primetech.primetech_store.config.webConfigImage;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigImage implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/userImage/**", "/productImage/**")
                .addResourceLocations("file:images/public/userImage/", "file:images/public/productImage/");
    }
}