package com.ohayoyo.gateway.http.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 蓝明乐
 */
public interface HttpGatewayMessageConverters extends List<HttpMessageConverter<?>> {

    boolean ROME_PRESENT = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", HttpGatewayMessageConverters.class.getClassLoader());

    boolean JAXB_2_PRESENT = ClassUtils.isPresent("javax.xml.bind.Binder", HttpGatewayMessageConverters.class.getClassLoader());

    boolean JACKSON_2_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", HttpGatewayMessageConverters.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", HttpGatewayMessageConverters.class.getClassLoader());

    boolean JACKSON_2_XML_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", HttpGatewayMessageConverters.class.getClassLoader());

    boolean GSON_PRESENT = ClassUtils.isPresent("com.google.gson.Gson", HttpGatewayMessageConverters.class.getClassLoader());

    Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    boolean hasEmptyConversionService();

    ConversionService getConversionService();

    HttpGatewayMessageConverters setConversionService(ConversionService conversionService);

}
