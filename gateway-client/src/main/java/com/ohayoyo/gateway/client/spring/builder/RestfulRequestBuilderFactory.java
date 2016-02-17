package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

public class RestfulRequestBuilderFactory implements FactoryBean<RestfulRequestBuilder> {

    public static ConversionService notConfigConversionService;

    @Autowired(required = false)
    private ConversionService conversionService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public RestfulRequestBuilder getObject() throws Exception {
        if (null == conversionService) {
            try {
                conversionService = this.applicationContext.getBean(ConversionService.class);
            } catch (Exception ex) {
                //none
            }
            if (null == conversionService) {
                if (null == notConfigConversionService) {
                    notConfigConversionService = new DefaultFormattingConversionService();
                }
                conversionService = notConfigConversionService;
            }
        }
        return RestfulRequestBuilder.newInstance().conversionService(conversionService);
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulRequestBuilder.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
