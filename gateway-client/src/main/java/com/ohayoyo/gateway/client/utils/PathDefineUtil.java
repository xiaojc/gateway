package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.core.PathDefine;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathDefineUtil {

    private static final String PATH_DELIMITER = "/";

    public static final boolean hasPathDelimiter(String path) {
        if (!StringUtils.isEmpty(path)) {
            return path.indexOf(PATH_DELIMITER) != -1;
        }
        return false;
    }

    public static void putPathSegments(String path, List<String> values) {
        if (!StringUtils.isEmpty(path)) {
            if (hasPathDelimiter(path)) {
                List<String> paths = Arrays.asList(path.split(PATH_DELIMITER));
                values.addAll(paths);
            } else {
                values.add(path);
            }
        }
    }

    public static String[] pathSegments(PathDefine pathDefine) {
        List<String> names = new ArrayList<String>();
        if (null != pathDefine) {
            String project = pathDefine.getProject();
            String module = pathDefine.getModule();
            String operate = pathDefine.getOperate();
            String resource = pathDefine.getResource();
            putPathSegments(project, names);
            putPathSegments(module, names);
            putPathSegments(operate, names);
            putPathSegments(resource, names);
        }
        int pathSegmentsSize = names.size();
        String[] pathSegments = new String[pathSegmentsSize];
        return names.toArray(pathSegments);
    }

}
