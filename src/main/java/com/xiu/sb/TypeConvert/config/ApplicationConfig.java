package com.xiu.sb.TypeConvert.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by jiajun.chen on 2018/4/13.
 */

//@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {


    /**
     * 添加静态资源文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/img").addResourceLocations("/img/");
        registry.addResourceHandler("/static/css").addResourceLocations("/css/");
        registry.addResourceHandler("/static/js").addResourceLocations("/js/");
        registry.addResourceHandler("/static/swf").addResourceLocations("/swf/");
        registry.addResourceHandler("/static/media").addResourceLocations("/media/");
    }

    /**
     * 添加自定义的Converters和Formatters.
     */
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        //添加字符串转换list的自定义转换器
        registry.addConverter(new StringToListConverter());
        //添加字符串转换Date的自定义转换器
        registry.addConverter(new StringToDateConverter());
    }

}
