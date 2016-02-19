package com.ohayoyo.gateway.client.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 蓝明乐
 */
public final class ApplicationContextUtils {

    public static <T> T tryAdaptiveFirstBean(ApplicationContext applicationContext, Class<T> type) throws Exception {
        String[] beanNames = applicationContext.getBeanNamesForType(type);
        if (!ObjectUtils.isEmpty(beanNames)) {
            List<String> beanNameList = Arrays.asList(beanNames);
            return applicationContext.getBean(beanNameList.iterator().next(), type);
        }
        return null;
    }

}
