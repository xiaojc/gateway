package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.PathDefine;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 路径值定义工具
 */
public class PathDefineUtil {

    /**
     * 路径值分隔符
     */
    private static final String PATH_DELIMITER = "/";

    /**
     * 是否存在路径值分隔符
     *
     * @param path 路径值
     * @return 返回是否存在路径值分隔符
     */
    public static final boolean hasPathDelimiter(String path) {
        if (!StringUtils.isEmpty(path)) {
            return path.indexOf(PATH_DELIMITER) != -1;
        }
        return false;
    }

    /**
     * 把路径分片项放入到某个集合中
     *
     * @param path   路径值
     * @param values 集合
     */
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

    /**
     * 把一个路径定义分片到一个字符串数组中
     *
     * @param pathDefine 路径值定义
     * @return 返回分片的路径值字符串数组
     */
    public static String[] pathSegments(PathDefine pathDefine) {
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
        int pathSegmentsSize = names.size();
        String[] pathSegments = new String[pathSegmentsSize];
        return names.toArray(pathSegments);
    }

}
