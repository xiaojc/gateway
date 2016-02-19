package com.ohayoyo.gateway.http.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.util.ObjectUtils;

import javax.xml.transform.Source;
import java.util.ArrayList;

/**
 * @author 蓝明乐
 */
public class HttpGatewayMessageConvertersHandler extends ArrayList<HttpMessageConverter<?>> implements HttpGatewayMessageConverters {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayMessageConverters.class);

    private ConversionService conversionService = null;

    private ObjectToStringHttpMessageConverter objectToStringHttpMessageConverter = null;

    public HttpGatewayMessageConvertersHandler() {
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

    @Override
    public boolean hasEmptyConversionService() {
        return ObjectUtils.isEmpty(this.conversionService);
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public HttpGatewayMessageConvertersHandler setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
        if (!ObjectUtils.isEmpty(this.conversionService)) {
            this.objectToStringHttpMessageConverter = new ObjectToStringHttpMessageConverter(this.conversionService, DEFAULT_CHARSET);
            this.add(this.objectToStringHttpMessageConverter);
        } else if (!ObjectUtils.isEmpty(this.objectToStringHttpMessageConverter)) {
            this.remove(this.objectToStringHttpMessageConverter);
        }
        return this;
    }

}
