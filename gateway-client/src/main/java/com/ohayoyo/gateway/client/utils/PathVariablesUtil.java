package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.http.PathDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathVariablesUtil {

    public static boolean hasExpandNames(PathDefine pathDefine){
        return CollectionUtils.isEmpty(expandNames(pathDefine)) ;
    }

    public static Set<String> expandNames(PathDefine pathDefine) {
        Set<String> expandNames = new HashSet<String>();
        List<String> pathSegments = PathDefineUtil.pathSegmentsAsList(pathDefine);
        for (String pathSegment : pathSegments) {
            Set<String> captureNames = captureNames(pathSegment);
            if (!CollectionUtils.isEmpty(captureNames)) {
                expandNames.addAll(captureNames);
            }
        }
        return expandNames;
    }

    public static Set<String> captureNames(String pathSegment) {
        Set<String> captureNames = new HashSet<String>();
        if (!StringUtils.isEmpty(pathSegment)) {
            int formIndex = 0;
            int len = pathSegment.length();
            if (len == 0) {
                return captureNames;
            }
            while (true) {
                int startIndex = pathSegment.indexOf("{", formIndex);
                int endIndex = pathSegment.indexOf("}", formIndex);
                if (startIndex == -1 || endIndex == -1) {
                    break;
                }
                String segment = pathSegment.substring((startIndex + 1), endIndex);
                captureNames.add(segment);
                formIndex = endIndex + 1;
                if (formIndex >= len) {
                    break;
                }
            }
        }
        return captureNames;
    }

}
