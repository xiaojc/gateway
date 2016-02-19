package com.ohayoyo.gateway.test.converter;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import org.springframework.core.convert.ConversionService;

public class DDQHttpGatewayMessageConverters extends HttpGatewayMessageConverters {

    public DDQHttpGatewayMessageConverters() {
        this.add(new DDQHttpMessageConverter());
    }

}
