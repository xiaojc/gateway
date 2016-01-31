package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.restful.RestfulExecutor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

public interface GatewayConfig {

    boolean romePresent = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", RestfulExecutor.class.getClassLoader());

    boolean jaxb2Present = ClassUtils.isPresent("javax.xml.bind.Binder", RestfulExecutor.class.getClassLoader());

    boolean jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", RestfulExecutor.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", RestfulExecutor.class.getClassLoader());

    boolean jackson2XmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", RestfulExecutor.class.getClassLoader());

    boolean gsonPresent = ClassUtils.isPresent("com.google.gson.Gson", RestfulExecutor.class.getClassLoader());

    Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    ConversionService getConversionService();

    Set<Class<?>> getAutoRecognitionClassSupports();

    List<HttpMessageConverter<?>> getHttpMessageConverters();

}
