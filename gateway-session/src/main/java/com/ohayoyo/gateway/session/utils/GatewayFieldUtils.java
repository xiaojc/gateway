package com.ohayoyo.gateway.session.utils;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.GatewayField;
import com.ohayoyo.gateway.define.core.GatewayType;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
@SuppressWarnings("unchecked")
public final class GatewayFieldUtils {

    public static boolean isExistEmptyFieldName(Set<GatewayField> fields) {
        if (!CollectionUtils.isEmpty(fields)) {
            for (GatewayField field : fields) {
                String name = field.getName();
                if (StringUtils.isEmpty(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Set<String> getFieldNames(Set<GatewayField> fields) {
        Set<String> parameterNames = new HashSet<String>();
        if (!CollectionUtils.isEmpty(fields)) {
            for (GatewayField fieldDefine : fields) {
                String name = fieldDefine.getName();
                parameterNames.add(name);
            }
        }
        return parameterNames;
    }

    public static GatewayField findFieldByName(String name, Set<GatewayField> fields) {
        if (StringUtils.isEmpty(name) && !CollectionUtils.isEmpty(fields)) {
            for (GatewayField fieldDefine : fields) {
                String parameterName = fieldDefine.getName();
                if (name.equals(parameterName)) {
                    return fieldDefine;
                }
            }
        }
        return null;
    }

    public static String resolveFieldDefaultValueToString(GatewayField field, ConversionService conversionService, GatewayContainer container) {
        String resultString = null;
        Object defaultValue = field.getDefaultValue();
        if (!ObjectUtils.isEmpty(defaultValue)) {
            Object result = null;
            GatewayType type = field.getType();
            GatewayTypeResolver typeResolver = container.getGatewayTypeResolver();
            Class<?> sourceType = defaultValue.getClass();
            Class<?> targetType = typeResolver.resolve(type);
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
