package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.converter.*;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Restful网关配置
 */
public class RestfulConfig implements GatewayConfig {

    /**
     * 转换服务
     */
    private ConversionService conversionService;

    /**
     * HTTP消息转换器集合
     */
    private List<HttpMessageConverter<?>> httpMessageConverters;

    /**
     * 构建一个默认的Restful网关配置
     */
    public RestfulConfig() {
        this(null, null, true);
    }

    /**
     * 构建一个指定其他HTTP消息转换器集合的Restful网关配置
     *
     * @param otherHttpMessageConverters 其他HTTP消息转换器集合
     */
    public RestfulConfig(List<HttpMessageConverter<?>> otherHttpMessageConverters) {
        this(otherHttpMessageConverters, null, true);
    }

    /**
     * 构建一个指定其他HTTP消息转换器集并指定是否使用默认配置的Restful网关配置
     *
     * @param otherHttpMessageConverters 其他HTTP消息转换器集合
     * @param isDefaultConfig            是否使用默认配置
     */
    public RestfulConfig(List<HttpMessageConverter<?>> otherHttpMessageConverters, boolean isDefaultConfig) {
        this(otherHttpMessageConverters, null, isDefaultConfig);
    }

    /**
     * 构建一个指定其他HTTP消息转换器集和转换服务并指定是否使用默认配置的Restful网关配置
     *
     * @param otherHttpMessageConverters 其他HTTP消息转换器集合
     * @param conversionService          转换服务
     * @param isDefaultConfig            是否使用默认配置
     */
    public RestfulConfig(List<HttpMessageConverter<?>> otherHttpMessageConverters, ConversionService conversionService, boolean isDefaultConfig) {
        this(null, otherHttpMessageConverters, conversionService, isDefaultConfig);
    }

    /**
     * 构建一个指定参数的 Restful网关配置
     *
     * @param httpMessageConverters      HTTP消息转换器集合
     * @param otherHttpMessageConverters 其他HTTP消息转换器集合
     * @param conversionService          转换服务
     * @param isDefaultConfig            是否使用默认配置
     */
    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, List<HttpMessageConverter<?>> otherHttpMessageConverters, ConversionService conversionService, boolean isDefaultConfig) {
        this.httpMessageConverters = httpMessageConverters;
        this.conversionService = conversionService;
        if (isDefaultConfig) { //如果使用默认配置
            //配置默认转换服务
            this.configDefaultConversionService();
            //配置默认自动识别支持的类型集合
            //配置默认HTTP消息转换器集合
            this.configDefaultHttpMessageConverters();
        }
        //配置其他HTTP消息转换器集合
        this.configOtherHttpMessageConverters(otherHttpMessageConverters);
    }

    /**
     * 配置默认转换服务
     */
    protected void configDefaultConversionService() {
        if (null == this.conversionService) {
            //更改默认的转换服务为格式化转换服务
            //this.conversionService = new DefaultFormattingConversionService();
            //默认使用默认转换服务
            this.conversionService = new DefaultConversionService();
        }
    }

    /**
     * 配置默认HTTP消息转换器集合
     */
    protected void configDefaultHttpMessageConverters() {
        this.httpMessageConverters = new ArrayList<HttpMessageConverter<?>>();
        this.httpMessageConverters.add(new ByteArrayHttpMessageConverter());
        this.httpMessageConverters.add(new StringHttpMessageConverter(DEFAULT_CHARSET));
        this.httpMessageConverters.add(new FormHttpMessageConverter());
        this.httpMessageConverters.add(new BufferedImageHttpMessageConverter());
        this.httpMessageConverters.add(new ResourceHttpMessageConverter());
        this.httpMessageConverters.add(new SourceHttpMessageConverter<Source>());
        this.httpMessageConverters.add(new AllEncompassingFormHttpMessageConverter());
        if (null != this.conversionService) {
            this.httpMessageConverters.add(new ObjectToStringHttpMessageConverter(this.conversionService, DEFAULT_CHARSET));
        }
        if (JACKSON_2_PRESENT) {
            this.httpMessageConverters.add(new MappingJackson2HttpMessageConverter());
        }
        if (JACKSON_2_XML_PRESENT) {
            this.httpMessageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        }
        if (JAXB_2_PRESENT) {
            this.httpMessageConverters.add(new Jaxb2CollectionHttpMessageConverter());
            this.httpMessageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        }
        if (GSON_PRESENT) {
            this.httpMessageConverters.add(new GsonHttpMessageConverter());
        }
        //MarshallingHttpMessageConverter : 暂时不支持
        //ProtobufHttpMessageConverter : 暂时不支持
        if (ROME_PRESENT) {
            this.httpMessageConverters.add(new AtomFeedHttpMessageConverter());
            this.httpMessageConverters.add(new RssChannelHttpMessageConverter());
        }
    }

    /**
     * 配置其他HTTP消息转换器集合
     *
     * @param otherHttpMessageConverters HTTP消息转换器集合
     */
    protected void configOtherHttpMessageConverters(List<HttpMessageConverter<?>> otherHttpMessageConverters) {
        if (!CollectionUtils.isEmpty(otherHttpMessageConverters)) {
            if (CollectionUtils.isEmpty(this.httpMessageConverters)) {
                this.httpMessageConverters = new ArrayList<HttpMessageConverter<?>>();
            }
            Set<String> classNames = new HashSet<String>();
            for (HttpMessageConverter<?> httpMessageConverter : this.httpMessageConverters) {
                String className = httpMessageConverter.getClass().getName();
                classNames.add(className);
            }
            for (HttpMessageConverter<?> otherHttpMessageConverter : otherHttpMessageConverters) {
                String className = otherHttpMessageConverter.getClass().getName();
                if (!classNames.contains(className)) {
                    classNames.add(className);
                    this.httpMessageConverters.add(otherHttpMessageConverter);
                }
            }
        }
    }

    /**
     * 获取转换服务
     *
     * @return 返回转换服务
     */
    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * 设置转换服务
     *
     * @param conversionService 转换服务
     * @return 返回网关配置
     */
    @Override
    public RestfulConfig setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        return this;
    }

    /**
     * 获取HTTP消息转换器集合
     *
     * @return 返回HTTP消息转换器集合
     */
    @Override
    public List<HttpMessageConverter<?>> getHttpMessageConverters() {
        return httpMessageConverters;
    }

    /**
     * 设置HTTP消息转换器集合
     *
     * @param httpMessageConverters HTTP消息转换器集合
     * @return 返回网关配置
     */
    @Override
    public RestfulConfig setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        this.httpMessageConverters = httpMessageConverters;
        return this;
    }

}
