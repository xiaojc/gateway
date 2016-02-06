package com.ohayoyo.gateway.http;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.*;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

import javax.xml.transform.Source;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DefaultHttpMessageConverters extends ArrayList<HttpMessageConverter<?>> implements List<HttpMessageConverter<?>> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private ConversionService conversionService;

    public DefaultHttpMessageConverters() {
        this(new DefaultFormattingConversionService());
    }

    public DefaultHttpMessageConverters(ConversionService conversionService) {
        this.conversionService = conversionService;
        this.configDefaultHttpMessageConverters();
    }

    protected void configDefaultHttpMessageConverters() {
        this.add(new ByteArrayHttpMessageConverter());
        this.add(new StringHttpMessageConverter(DEFAULT_CHARSET));
        this.add(new FormHttpMessageConverter());
        this.add(new BufferedImageHttpMessageConverter());
        this.add(new ResourceHttpMessageConverter());
        this.add(new SourceHttpMessageConverter<Source>());
        this.add(new AllEncompassingFormHttpMessageConverter());
        ConversionService conversionService = this.getConversionService();
        if (null != conversionService) {
            this.add(new ObjectToStringHttpMessageConverter(conversionService, DEFAULT_CHARSET));
        }
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

}
