package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.core.ResolveDataType;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ParameterDefineUtils {

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

    public static String resolveParameterDefaultValueToString(Parameter parameter, ConversionService conversionService) {
        String resultString = null;
        Object defaultValue = parameter.getDefaultValue();
        if (!ObjectUtils.isEmpty(defaultValue)) {
            Object result = null;
            String defineDataType = parameter.getDataType();
            String referenceClass = parameter.getReferenceClass();
            Class<?> sourceType = defaultValue.getClass();
            Class<?> targetType = ResolveDataType.DEFAULT.resolve(defineDataType, referenceClass);
            if (conversionService.canConvert(sourceType, targetType)) {
                result = conversionService.convert(defaultValue, targetType);
            }
            if (!ObjectUtils.isEmpty(result)) {
                Class<?> resultSourceType = result.getClass();
                Class<String> resultTargetType = String.class;
                if (resultSourceType.equals(resultTargetType)) {
                    resultString = (String) result;
                } else if (conversionService.canConvert(resultSourceType, resultTargetType)) {
                    resultString = conversionService.convert(result, resultTargetType);
                }
            }
        }
        return resultString;
    }

}
