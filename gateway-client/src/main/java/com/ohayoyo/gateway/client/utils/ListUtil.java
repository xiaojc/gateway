package com.ohayoyo.gateway.client.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class ListUtil {

    public static void putObjectListToStringList(List<?> objectList, List<String> stringList) {
        if ((!CollectionUtils.isEmpty(objectList))) {
            for (Object object : objectList) {
                if (!StringUtils.isEmpty(object)) {
                    stringList.add(object.toString());
                }
            }
        }
    }

}
