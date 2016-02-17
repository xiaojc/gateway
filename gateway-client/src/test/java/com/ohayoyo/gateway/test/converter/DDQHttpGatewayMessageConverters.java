package com.ohayoyo.gateway.test.converter;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import org.springframework.core.convert.ConversionService;

public class DDQHttpGatewayMessageConverters extends HttpGatewayMessageConverters {

    public DDQHttpGatewayMessageConverters(ConversionService conversionService) {
        super(conversionService);
        this.add(new DDQHttpMessageConverter());
    }

}
