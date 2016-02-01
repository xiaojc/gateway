package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
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
import org.springframework.util.MultiValueMap;

import javax.xml.transform.Source;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Restful网关配置
 */
public class RestfulConfig implements GatewayConfig {

    /**
     * 转换服务
     */
    private ConversionService conversionService;

    /**
     * 自动识别支持的类型集合
     */
    private Set<Class<?>> autoRecognitionClassSupports;

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
        this(null, otherHttpMessageConverters, conversionService, null, isDefaultConfig);
    }

    /**
     * 构建一个指定参数的 Restful网关配置
     *
     * @param httpMessageConverters        HTTP消息转换器集合
     * @param otherHttpMessageConverters   其他HTTP消息转换器集合
     * @param conversionService            转换服务
     * @param autoRecognitionClassSupports 自动识别支持的类型集合
     * @param isDefaultConfig              是否使用默认配置
     */
    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, List<HttpMessageConverter<?>> otherHttpMessageConverters, ConversionService conversionService, Set<Class<?>> autoRecognitionClassSupports, boolean isDefaultConfig) {
        this.httpMessageConverters = httpMessageConverters;
        this.conversionService = conversionService;
        this.autoRecognitionClassSupports = autoRecognitionClassSupports;
        if (isDefaultConfig) { //如果使用默认配置
            //配置默认转换服务
            this.configDefaultConversionService();
            //配置默认自动识别支持的类型集合
            this.configDefaultAutoRecognitionClassSupports();
            //配置默认HTTP消息转换器集合
            this.configDefaultHttpMessageConverters();
        } else if (CollectionUtils.isEmpty(this.autoRecognitionClassSupports)) { //如果自动识别支持的类型集合是空的
            //配置默认自动识别支持的类型集合
            this.configDefaultAutoRecognitionClassSupports();
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
     * 配置默认自动识别支持的类型集合
     */
    protected void configDefaultAutoRecognitionClassSupports() {
        this.autoRecognitionClassSupports = new HashSet<Class<?>>();
        this.autoRecognitionClassSupports.add(Byte[].class);
        this.autoRecognitionClassSupports.add(String.class);
        this.autoRecognitionClassSupports.add(Collection.class);
        this.autoRecognitionClassSupports.add(Map.class);
        this.autoRecognitionClassSupports.add(List.class);
        this.autoRecognitionClassSupports.add(Set.class);
        this.autoRecognitionClassSupports.add(MultiValueMap.class);
        this.autoRecognitionClassSupports.add(Resource.class);
        this.autoRecognitionClassSupports.add(Source.class);
        this.autoRecognitionClassSupports.add(BufferedImage.class);
        if (ROME_PRESENT) {
            this.autoRecognitionClassSupports.add(Feed.class);
            this.autoRecognitionClassSupports.add(Channel.class);
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
     * 获取自动识别支持的类型集合
     *
     * @return 返回自动识别支持的类型集合
     */
    @Override
    public Set<Class<?>> getAutoRecognitionClassSupports() {
        return autoRecognitionClassSupports;
    }

    /**
     * 设置自动识别支持的类型集合
     *
     * @param autoRecognitionClassSupports 自动识别支持的类型集合
     * @return 返回网关配置
     */
    @Override
    public RestfulConfig setAutoRecognitionClassSupports(Set<Class<?>> autoRecognitionClassSupports) {
        this.autoRecognitionClassSupports = autoRecognitionClassSupports;
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
