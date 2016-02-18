package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.core.Parameter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ParameterDefineUtil {

    public static boolean isExistEmptyParameterName(Set<Parameter> parameters) {
        if (!CollectionUtils.isEmpty(parameters)) {
            for (Parameter parameter : parameters) {
                String name = parameter.getName();
                if (StringUtils.isEmpty(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Set<String> getParameterNames(Set<Parameter> parameters) {
        Set<String> parameterNames = new HashSet<String>();
        if (!CollectionUtils.isEmpty(parameters)) {
            for (Parameter parameter : parameters) {
                String name = parameter.getName();
                parameterNames.add(name);
            }
        }
        return parameterNames;
    }

    public static Parameter findParameterDefineByName(String name, Set<Parameter> parameters) {
        if (StringUtils.isEmpty(name) && !CollectionUtils.isEmpty(parameters)) {
            for (Parameter parameter : parameters) {
                String parameterName = parameter.getName();
                if (name.equals(parameterName)) {
                    return parameter;
                }
            }
        }
        return null;
    }

}
