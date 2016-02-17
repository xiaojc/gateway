package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.core.PathDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class PathVariablesUtil {

    public static boolean hasExpandNames(PathDefine pathDefine) {
        return !CollectionUtils.isEmpty(expandVariableNames(pathDefine));
    }

    public static Set<String> expandVariableNames(PathDefine pathDefine) {
        Set<String> expandNames = new HashSet<String>();
        List<String> pathSegments = PathDefineUtil.pathSegmentsAsList(pathDefine);
        for (String pathSegment : pathSegments) {
            Set<String> captureNames = captureVariableNames(pathSegment);
            if (!CollectionUtils.isEmpty(captureNames)) {
                expandNames.addAll(captureNames);
            }
        }
        return expandNames;
    }

    private static Set<String> captureVariableNames(String pathSegment) {
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
