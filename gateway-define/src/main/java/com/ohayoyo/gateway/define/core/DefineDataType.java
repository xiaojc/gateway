package com.ohayoyo.gateway.define.core;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class DefineDataType implements ResolveDataType {

    @Override
    public Class<?> resolve(String dataType, String referenceClass) {

        if (!StringUtils.isEmpty(dataType)) {

            if (BYTE.equalsIgnoreCase(dataType)) {
                return byte.class;
            } else if (SHORT.equalsIgnoreCase(dataType)) {
                return short.class;
            } else if (INT.equalsIgnoreCase(dataType)) {
                return int.class;
            } else if (LONG.equalsIgnoreCase(dataType)) {
                return long.class;
            } else if (CHAR.equalsIgnoreCase(dataType)) {
                return char.class;
            } else if (FLOAT.equalsIgnoreCase(dataType)) {
                return float.class;
            } else if (DOUBLE.equalsIgnoreCase(dataType)) {
                return double.class;
            } else if (BOOLEAN.equalsIgnoreCase(dataType)) {
                return boolean.class;
            } else if (LIST.equalsIgnoreCase(dataType)) {
                return List.class;
            } else if (SET.equalsIgnoreCase(dataType)) {
                return Set.class;
            } else if (MAP.equalsIgnoreCase(dataType)) {
                return Map.class;
            } else if (STRING.equalsIgnoreCase(dataType)) {
                return String.class;
            } else if (FILE.equalsIgnoreCase(dataType)) {
                return File.class;
            } else if (OBJECT.equalsIgnoreCase(dataType)) {
                try {
                    return Class.forName(referenceClass);
                } catch (ClassNotFoundException ex) {
                    //none
                }
            }
        }
        return Object.class;
    }

}
