package com.ohayoyo.gateway.http;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 蓝明乐
 */
public interface GatewayHttpMessageConverters extends List<HttpMessageConverter<?>> {

    boolean ROME_PRESENT = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", GatewayHttpMessageConverters.class.getClassLoader());

    boolean JAXB_2_PRESENT = ClassUtils.isPresent("javax.xml.bind.Binder", GatewayHttpMessageConverters.class.getClassLoader());

    boolean JACKSON_2_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", GatewayHttpMessageConverters.class.getClassLoader())
            && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", GatewayHttpMessageConverters.class.getClassLoader());

    boolean JACKSON_2_XML_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", GatewayHttpMessageConverters.class.getClassLoader());

    boolean GSON_PRESENT = ClassUtils.isPresent("com.google.gson.Gson", GatewayHttpMessageConverters.class.getClassLoader());

    Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    boolean hasEmptyConversionService();

    ConversionService getConversionService();

    void setConversionService(ConversionService conversionService);

}
