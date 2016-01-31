package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.format.support.DefaultFormattingConversionService;
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

public class RestfulConfig implements GatewayConfig {

    private List<HttpMessageConverter<?>> httpMessageConverters;

    private ConversionService conversionService;

    private Set<Class<?>> autoRecognitionClassSupports;

    public RestfulConfig() {
        this(null, null, true);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> otherHttpMessageConverters) {
        this(otherHttpMessageConverters, null, true);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, boolean isDefaultConfig) {
        this(httpMessageConverters, null, isDefaultConfig);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, boolean isDefaultConfig) {
        this(httpMessageConverters, conversionService, null, isDefaultConfig);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, ConversionService conversionService, Set<Class<?>> autoRecognitionClassSupports, boolean isDefaultConfig) {
        this.httpMessageConverters = httpMessageConverters;
        this.conversionService = conversionService;
        this.autoRecognitionClassSupports = autoRecognitionClassSupports;
        if (isDefaultConfig) {
            this.configDefaultConversionService();
            this.configDefaultAutoRecognitionClassSupports();
            this.configDefaultHttpMessageConverters();
            this.configOtherHttpMessageConverters(httpMessageConverters);
        } else if (null == this.autoRecognitionClassSupports) {
            this.configDefaultAutoRecognitionClassSupports();
        }
    }

    private void configDefaultConversionService() {
        if (null == this.conversionService) {
            this.conversionService = new DefaultFormattingConversionService();
            //this.conversionService = new DefaultConversionService();

        }
    }

    public void configDefaultAutoRecognitionClassSupports() {
        this.autoRecognitionClassSupports = new HashSet<Class<?>>();
        if (romePresent) {
            this.autoRecognitionClassSupports.add(Feed.class);
            this.autoRecognitionClassSupports.add(Channel.class);
        }
        if (jackson2XmlPresent || jaxb2Present || jackson2Present || gsonPresent) {
            this.autoRecognitionClassSupports.add(Collection.class);
            this.autoRecognitionClassSupports.add(List.class);
            this.autoRecognitionClassSupports.add(Set.class);
            this.autoRecognitionClassSupports.add(Map.class);
        }
        this.autoRecognitionClassSupports.add(BufferedImage.class);
        this.autoRecognitionClassSupports.add(MultiValueMap.class);
        this.autoRecognitionClassSupports.add(String.class);
        this.autoRecognitionClassSupports.add(Resource.class);
        this.autoRecognitionClassSupports.add(Source.class);
        this.autoRecognitionClassSupports.add(byte[].class);
    }

    public void configDefaultHttpMessageConverters() {

        this.httpMessageConverters = new ArrayList<HttpMessageConverter<?>>();

        this.httpMessageConverters.add(new BufferedImageHttpMessageConverter());

        this.httpMessageConverters.add(new FormHttpMessageConverter());

        this.httpMessageConverters.add(new ByteArrayHttpMessageConverter());

        this.httpMessageConverters.add(new StringHttpMessageConverter(DEFAULT_CHARSET));

        this.httpMessageConverters.add(new ResourceHttpMessageConverter());

        this.httpMessageConverters.add(new SourceHttpMessageConverter<Source>());

        this.httpMessageConverters.add(new AllEncompassingFormHttpMessageConverter());

        if (null != this.conversionService) {
            this.httpMessageConverters.add(new ObjectToStringHttpMessageConverter(this.conversionService, DEFAULT_CHARSET));
        }

        //MarshallingHttpMessageConverter : 暂时不支持

        //ProtobufHttpMessageConverter : 暂时不支持

        if (romePresent) {
            this.httpMessageConverters.add(new AtomFeedHttpMessageConverter());
            this.httpMessageConverters.add(new RssChannelHttpMessageConverter());
        }

        if (jackson2XmlPresent) {
            this.httpMessageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        }

        if (jaxb2Present) {
            this.httpMessageConverters.add(new Jaxb2CollectionHttpMessageConverter());
            this.httpMessageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        }

        if (jackson2Present) {
            this.httpMessageConverters.add(new MappingJackson2HttpMessageConverter());
        }

        if (gsonPresent) {
            this.httpMessageConverters.add(new GsonHttpMessageConverter());
        }

    }

    public void configOtherHttpMessageConverters(List<HttpMessageConverter<?>> otherHttpMessageConverters) {
        if (!CollectionUtils.isEmpty(otherHttpMessageConverters)) {
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

    public List<HttpMessageConverter<?>> getHttpMessageConverters() {
        return httpMessageConverters;
    }

    public RestfulConfig setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        this.httpMessageConverters = httpMessageConverters;
        return this;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public RestfulConfig setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        return this;
    }

    public Set<Class<?>> getAutoRecognitionClassSupports() {
        return autoRecognitionClassSupports;
    }

    public RestfulConfig setAutoRecognitionClassSupports(Set<Class<?>> autoRecognitionClassSupports) {
        this.autoRecognitionClassSupports = autoRecognitionClassSupports;
        return this;
    }

}
