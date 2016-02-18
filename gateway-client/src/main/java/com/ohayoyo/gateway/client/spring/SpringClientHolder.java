package com.ohayoyo.gateway.client.spring;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.ObjectUtils;

public class SpringClientHolder {

    private static ConversionService GLOBAL_CONVERSION_SERVICE;

    private static volatile Object GLOBAL_CONVERSION_SERVICE_LOCKED = new Object();

    public static ConversionService globalConversionServiceHolder() {
        if (ObjectUtils.isEmpty(GLOBAL_CONVERSION_SERVICE)) {
            synchronized (GLOBAL_CONVERSION_SERVICE_LOCKED) {
                if (ObjectUtils.isEmpty(GLOBAL_CONVERSION_SERVICE)) {
                    GLOBAL_CONVERSION_SERVICE = new DefaultFormattingConversionService();
                }
            }
        }
        return GLOBAL_CONVERSION_SERVICE;
    }

}
