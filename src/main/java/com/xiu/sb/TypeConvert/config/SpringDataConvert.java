package com.xiu.sb.TypeConvert.config;

import com.xiu.sb.TypeConvert.model.OrderDetail;
import com.xiu.sb.TypeConvert.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class SpringDataConvert {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * 增加字符串转换为List集合
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            //添加字符串转换为list集合的转换机器
            genericConversionService.addConverter(new StringToOrderDetailListConverter());
            genericConversionService.addConverter(new StringToUserListConverter());
            //添加字符串转换为日期类型的字符串
            //genericConversionService.addConverter(new StringToDateConverter());

        }
    }
}