package com.ohayoyo.gateway.http;

import org.springframework.core.convert.ConversionService;
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
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.xml.transform.Source;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GatewayHttpMessageConverters extends ArrayList<HttpMessageConverter<?>> implements List<HttpMessageConverter<?>> {

    public static final boolean ROME_PRESENT = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", GatewayHttpMessageConverters.class.getClassLoader());

    public static final boolean JAXB_2_PRESENT = ClassUtils.isPresent("javax.xml.bind.Binder", GatewayHttpMessageConverters.class.getClassLoader());

    public static final boolean JACKSON_2_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", GatewayHttpMessageConverters.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", GatewayHttpMessageConverters.class.getClassLoader());

    public static final boolean JACKSON_2_XML_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", GatewayHttpMessageConverters.class.getClassLoader());

    public static final boolean GSON_PRESENT = ClassUtils.isPresent("com.google.gson.Gson", GatewayHttpMessageConverters.class.getClassLoader());

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private ConversionService conversionService;

    public GatewayHttpMessageConverters(ConversionService conversionService) {
        Assert.notNull(conversionService);
        this.conversionService = conversionService;
        this.configDefaultHttpMessageConverters();
    }

    protected void configDefaultHttpMessageConverters() {
        this.add(new ByteArrayHttpMessageConverter());
        this.add(new StringHttpMessageConverter(DEFAULT_CHARSET));
        this.add(new ResourceHttpMessageConverter());
        this.add(new SourceHttpMessageConverter<Source>());
        this.add(new FormHttpMessageConverter());
        this.add(new AllEncompassingFormHttpMessageConverter());
        this.add(new BufferedImageHttpMessageConverter());
        this.add(new ObjectToStringHttpMessageConverter(this.conversionService, DEFAULT_CHARSET));
        //MarshallingHttpMessageConverter : 暂时不支持
        //ProtobufHttpMessageConverter : 暂时不支持
        if (ROME_PRESENT) {
            this.add(new AtomFeedHttpMessageConverter());
            this.add(new RssChannelHttpMessageConverter());
        }
        if (JACKSON_2_XML_PRESENT) {
            this.add(new MappingJackson2XmlHttpMessageConverter());
        } else if (JAXB_2_PRESENT) {
            this.add(new Jaxb2CollectionHttpMessageConverter());
            this.add(new Jaxb2RootElementHttpMessageConverter());
        }
        if (JACKSON_2_PRESENT) {
            this.add(new MappingJackson2HttpMessageConverter());
        } else if (GSON_PRESENT) {
            this.add(new GsonHttpMessageConverter());
        }
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

}
