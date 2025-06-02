package com.bistu.SecondStore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置静态资源处理器
 * 你的应用接收到类似 /img/somefile.jpg 的请求时，
 * Spring Boot 将会去 D:\\SecondStore\\img 目录下找到相应的文件并返回给客户端，使
 * 得上传的文件可以通过URL直接访问。
 */
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {
    private String UPLOAD_FOLDER = "D:\\SecondStore\\img";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_FOLDER + "/");
    }
}
