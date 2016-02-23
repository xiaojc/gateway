package com.ohayoyo.gateway.utils;

import com.ohayoyo.gateway.define.GatewayPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author 蓝明乐
 */
public final class GatewayPathUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayPathUtils.class);

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

    public static List<String> pathSegmentsAsList(GatewayPath path) {
        List<String> names = new ArrayList<String>();
        if (null != path) {
            String project = path.getProject();
            String module = path.getModule();
            String operate = path.getOperate();
            String resource = path.getResource();
            pathSegmentItems(project, names);
            pathSegmentItems(module, names);
            pathSegmentItems(operate, names);
            pathSegmentItems(resource, names);
        }
        return names;
    }

    public static String[] pathSegments(GatewayPath path) {
        List<String> names = pathSegmentsAsList(path);
        int pathSegmentsSize = names.size();
        String[] pathSegments = new String[pathSegmentsSize];
        return names.toArray(pathSegments);
    }

    public static boolean hasExpandNames(GatewayPath path) {
        return !CollectionUtils.isEmpty(expandVariableNames(path));
    }

    public static Set<String> expandVariableNames(GatewayPath path) {
        Set<String> expandNames = new HashSet<String>();
        List<String> pathSegments = GatewayPathUtils.pathSegmentsAsList(path);
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
