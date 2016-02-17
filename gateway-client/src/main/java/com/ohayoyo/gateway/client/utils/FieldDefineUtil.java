package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.ParameterDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class FieldDefineUtil {

    public static boolean hasEmptyFieldName(Set<ParameterDefine> parameterDefines) {
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

    public static Set<String> getFieldNames(Set<ParameterDefine> parameterDefines) {
        Set<String> fieldNames = new HashSet<String>();
        if (!CollectionUtils.isEmpty(parameterDefines)) {
            for (ParameterDefine parameterDefine : parameterDefines) {
                String name = parameterDefine.getName();
                fieldNames.add(name);
            }
        }
        return fieldNames;
    }

}
