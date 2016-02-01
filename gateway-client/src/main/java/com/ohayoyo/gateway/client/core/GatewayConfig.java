package com.ohayoyo.gateway.client.core;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

/**
 * 网关配置
 */
public interface GatewayConfig {

    /**
     * 判断支持ROME类路径是否存在
     */
    boolean ROME_PRESENT = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", GatewayConfig.class.getClassLoader());

    /**
     * 判断支持JAXB_2类路径是否存在
     */
    boolean JAXB_2_PRESENT = ClassUtils.isPresent("javax.xml.bind.Binder", GatewayConfig.class.getClassLoader());

    /**
     * 判断支持JACKSON_2_JSON类路径是否存在
     */
    boolean JACKSON_2_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", GatewayConfig.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", GatewayConfig.class.getClassLoader());

    /**
     * 判断支持JACKSON_2_XML类路径是否存在
     */
    boolean JACKSON_2_XML_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", GatewayConfig.class.getClassLoader());

    /**
     * 判断支持GSON类路径是否存在
     */
    boolean GSON_PRESENT = ClassUtils.isPresent("com.google.gson.Gson", GatewayConfig.class.getClassLoader());

    /**
     * 默认字符集编码:UTF-8
     */
    Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 获取转换服务
     *
     * @return 返回转换服务
     */
    ConversionService getConversionService();

    /**
     * 设置转换服务
     *
     * @param conversionService 转换服务
     * @return 返回网关配置
     */
    GatewayConfig setConversionService(ConversionService conversionService);

    /**
     * 获取自动识别支持的类型集合
     *
     * @return 返回自动识别支持的类型集合
     */
    Set<Class<?>> getAutoRecognitionClassSupports();

    /**
     * 设置自动识别支持的类型集合
     *
     * @param autoRecognitionClassSupports 自动识别支持的类型集合
     * @return 返回网关配置
     */
    GatewayConfig setAutoRecognitionClassSupports(Set<Class<?>> autoRecognitionClassSupports);

    /**
     * 获取HTTP消息转换器集合
     *
     * @return 返回HTTP消息转换器集合
     */
    List<HttpMessageConverter<?>> getHttpMessageConverters();

    /**
     * 设置HTTP消息转换器集合
     *
     * @param httpMessageConverters HTTP消息转换器集合
     * @return 返回网关配置
     */
    GatewayConfig setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters);

}
