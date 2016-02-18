package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.ParameterDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ParameterDefineUtil {

    public static boolean isExistEmptyParameterName(Set<ParameterDefine> parameterDefines) {
        if (!CollectionUtils.isEmpty(parameterDefines)) {
            for (ParameterDefine parameterDefine : parameterDefines) {
                String name = parameterDefine.getName();
                if (StringUtils.isEmpty(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Set<String> getParameterNames(Set<ParameterDefine> parameterDefines) {
        Set<String> parameterNames = new HashSet<String>();
        if (!CollectionUtils.isEmpty(parameterDefines)) {
            for (ParameterDefine parameterDefine : parameterDefines) {
                String name = parameterDefine.getName();
                parameterNames.add(name);
            }
        }
        return parameterNames;
    }

    public static ParameterDefine findParameterDefineByName(String name, Set<ParameterDefine> parameterDefines) {
        if (StringUtils.isEmpty(name) && !CollectionUtils.isEmpty(parameterDefines)) {
            for (ParameterDefine parameterDefine : parameterDefines) {
                String parameterName = parameterDefine.getName();
                if (name.equals(parameterName)) {
                    return parameterDefine;
                }
            }
        }
        return null;
    }

}
