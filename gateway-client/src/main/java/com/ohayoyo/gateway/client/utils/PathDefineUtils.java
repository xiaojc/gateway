package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.http.PathDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author 蓝明乐
 */
public class PathDefineUtils {

    private static final String PATH_DELIMITER = "/";

    public static final boolean hasPathDelimiter(String path) {
        if (!StringUtils.isEmpty(path)) {
            return path.indexOf(PATH_DELIMITER) != -1;
        }
        return false;
    }

    public static void pathSegmentItems(String path, List<String> values) {
        if (!StringUtils.isEmpty(path)) {
            if (hasPathDelimiter(path)) {
                List<String> paths = Arrays.asList(path.split(PATH_DELIMITER));
                values.addAll(paths);
            } else {
                values.add(path);
            }
        }
    }

    public static List<String> pathSegmentsAsList(PathDefine pathDefine) {
        List<String> names = new ArrayList<String>();
        if (null != pathDefine) {
            String project = pathDefine.getProject();
            String module = pathDefine.getModule();
            String operate = pathDefine.getOperate();
            String resource = pathDefine.getResource();
            pathSegmentItems(project, names);
            pathSegmentItems(module, names);
            pathSegmentItems(operate, names);
            pathSegmentItems(resource, names);
        }
        return names;
    }

    public static String[] pathSegments(PathDefine pathDefine) {
        List<String> names = pathSegmentsAsList(pathDefine);
        int pathSegmentsSize = names.size();
        String[] pathSegments = new String[pathSegmentsSize];
        return names.toArray(pathSegments);
    }

    public static boolean hasExpandNames(PathDefine pathDefine) {
        return !CollectionUtils.isEmpty(expandVariableNames(pathDefine));
    }

    public static Set<String> expandVariableNames(PathDefine pathDefine) {
        Set<String> expandNames = new HashSet<String>();
        List<String> pathSegments = PathDefineUtils.pathSegmentsAsList(pathDefine);
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
