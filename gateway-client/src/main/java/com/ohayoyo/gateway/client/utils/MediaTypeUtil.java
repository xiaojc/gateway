package com.ohayoyo.gateway.client.utils;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class MediaTypeUtil {

    public static List<MediaType> supportedMediaTypes(HttpMessageConverter<?> httpMessageConverter) {
        List<MediaType> supportedMediaTypes = httpMessageConverter.getSupportedMediaTypes();
        List<MediaType> resultSupportedMediaTypes = new ArrayList<MediaType>(supportedMediaTypes.size());
        for (MediaType supportedMediaType : supportedMediaTypes) {
            if (supportedMediaType.getCharSet() != null) {
                supportedMediaType = new MediaType(supportedMediaType.getType(), supportedMediaType.getSubtype());
            }
            resultSupportedMediaTypes.add(supportedMediaType);
        }
        return resultSupportedMediaTypes;
    }

}
