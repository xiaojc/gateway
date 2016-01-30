package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayPart;
import com.ohayoyo.gateway.client.parts.*;
import org.springframework.core.convert.ConversionService;
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
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Source;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestfulConfig implements GatewayConfig {

    private static boolean romePresent = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", RestfulExecutor.class.getClassLoader());

    private static final boolean jaxb2Present = ClassUtils.isPresent("javax.xml.bind.Binder", RestfulExecutor.class.getClassLoader());

    private static final boolean jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", RestfulExecutor.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", RestfulExecutor.class.getClassLoader());

    private static final boolean jackson2XmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", RestfulExecutor.class.getClassLoader());

    private static final boolean gsonPresent = ClassUtils.isPresent("com.google.gson.Gson", RestfulExecutor.class.getClassLoader());

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private List<HttpMessageConverter<?>> httpMessageConverters;

    private Set<Class<? extends GatewayPart<?>>> gatewayPartClasses;

    private ConversionService conversionService;

    public RestfulConfig() {
        this(null, null, true);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> otherHttpMessageConverters) {
        this(otherHttpMessageConverters, null, true);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, Set<Class<? extends GatewayPart<?>>> gatewayPartClasses, boolean isDefaultConfig) {
        this(httpMessageConverters, gatewayPartClasses, null, isDefaultConfig);
    }

    public RestfulConfig(List<HttpMessageConverter<?>> httpMessageConverters, Set<Class<? extends GatewayPart<?>>> gatewayPartClasses, ConversionService conversionService, boolean isDefaultConfig) {
        this.httpMessageConverters = httpMessageConverters;
        this.gatewayPartClasses = gatewayPartClasses;
        this.conversionService = conversionService;
        if (isDefaultConfig) {
            if (null == this.conversionService) {
                this.conversionService = new DefaultFormattingConversionService();
            }
            this.configDefaultGatewayPartClasses();
            this.configDefaultHttpMessageConverters();
            this.configOtherGatewayPartClasses(gatewayPartClasses);
            this.configOtherHttpMessageConverters(httpMessageConverters);
        }
    }

    public void configDefaultGatewayPartClasses() {
        this.gatewayPartClasses = new HashSet<Class<? extends GatewayPart<?>>>();
        this.gatewayPartClasses.add(UriPart.class);
        this.gatewayPartClasses.add(HttpMethodPart.class);
        this.gatewayPartClasses.add(RequestCallbackPart.class);
        this.gatewayPartClasses.add(ResponseExtractorPart.class);
        this.gatewayPartClasses.add(ResponseWrapperPart.class);
    }

    public void configOtherGatewayPartClasses(Set<Class<? extends GatewayPart<?>>> otherGatewayPartClasses) {
        if (!CollectionUtils.isEmpty(otherGatewayPartClasses)) {
            this.gatewayPartClasses.addAll(otherGatewayPartClasses);
        }
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

    public Set<Class<? extends GatewayPart<?>>> getGatewayPartClasses() {
        return gatewayPartClasses;
    }

    public RestfulConfig setGatewayPartClasses(Set<Class<? extends GatewayPart<?>>> gatewayPartClasses) {
        this.gatewayPartClasses = gatewayPartClasses;
        return this;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public RestfulConfig setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        return this;
    }

}
