package com.ohayoyo.gateway.test.converter;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConvertersHandler;

public class DDQHttpGatewayMessageConverters extends HttpGatewayMessageConvertersHandler {

    public DDQHttpGatewayMessageConverters() {
        super();
        this.add(new DDQHttpMessageConverter());
    }

}
