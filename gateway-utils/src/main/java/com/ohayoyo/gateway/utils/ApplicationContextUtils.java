package com.ohayoyo.gateway.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 蓝明乐
 */
public final class ApplicationContextUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextUtils.class);

    public static <T> T tryScanFirstBean(ApplicationContext applicationContext, Class<T> type) {
        T t = null;
        try {
            String[] beanNames = applicationContext.getBeanNamesForType(type);
            if (!ObjectUtils.isEmpty(beanNames)) {
                List<String> beanNameList = Arrays.asList(beanNames);
                t = applicationContext.getBean(beanNameList.iterator().next(), type);
            }
        } catch (Exception ex) {
            LOGGER.debug("尝试扫描适配类型{}失败,信息:{}", type, ex);
        }
        return t;
    }

}
