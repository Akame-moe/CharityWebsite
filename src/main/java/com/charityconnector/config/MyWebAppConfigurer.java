package com.charityconnector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 18274 on 2017/8/9.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // !!! The Resource Locations Path should start with "file:" !!!
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String projectPath =path.replace("out/production/classes/","")+"src/main/logo-images/";
        registry.addResourceHandler("/logo-images/**").addResourceLocations("file:"+projectPath);
        super.addResourceHandlers(registry);
    }
}
