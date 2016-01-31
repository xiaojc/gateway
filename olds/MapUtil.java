package com.ohayoyo.gateway.client.utils;

import org.springframework.util.*;

import java.util.*;

public class MapUtil {

    private static final String EMPTY_STRING = "";

    public static MultiValueMap<String, String> stringAndObjectMapToStringAndStringMultiValueMap(Map<String, Object> stringAndObjectMap) {
        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
        Set<Map.Entry<String, Object>> stringAndObjectMapEntries = stringAndObjectMap.entrySet();
        for (Map.Entry<String, Object> stringAndObjectMapEntry : stringAndObjectMapEntries) {
            String key = stringAndObjectMapEntry.getKey();
            Object value = stringAndObjectMapEntry.getValue();
            if (ObjectUtils.isEmpty(value)) {
                linkedMultiValueMap.set(key, EMPTY_STRING);
            } else {
                if (ObjectUtils.isArray(value)) {
                    Object[] objects = (Object[]) value;
                    for (Object object : objects) {
                        if (ObjectUtils.isEmpty(object)) {
                            linkedMultiValueMap.add(key, EMPTY_STRING);
                        } else {
                            linkedMultiValueMap.add(key, object.toString());
                        }
                    }
                } else {
                    linkedMultiValueMap.set(key, value.toString());
                }
            }
        }
        return linkedMultiValueMap;
    }

    public static Map<String, List<String>> stringObjectMapToStringListMap(Map<String, Object> stringObjectMap, String valueAsMapDelimiter) {
        Map<String, List<String>> stringListMap = new HashMap<String, List<String>>();
        if (!CollectionUtils.isEmpty(stringObjectMap)) {
            Set<Map.Entry<String, Object>> stringObjectMapEntries = stringObjectMap.entrySet();
            for (Map.Entry<String, Object> stringObjectMapEntry : stringObjectMapEntries) {
                String key = stringObjectMapEntry.getKey();
                Object value = stringObjectMapEntry.getValue();
                if (null == value) {
                    continue;
                }
                List<String> values = new ArrayList<String>();
                if (value instanceof String) {
                    values.add(value.toString());
                } else if (ObjectUtils.isArray(value)) {
                    Object[] valueObjects = (Object[]) value;
                    List<?> valueObjectList = Arrays.asList(valueObjects);
                    ListUtil.putObjectListToStringList(valueObjectList, values);
                } else if (value instanceof List) {
                    List<?> valueObjectList = (List<?>) value;
                    ListUtil.putObjectListToStringList(valueObjectList, values);
                } else if (value instanceof Map) {
                    Map<Object, Object> valueMap = (Map<Object, Object>) value;
                    Set<Map.Entry<Object, Object>> valueMapEntries = valueMap.entrySet();
                    for (Map.Entry<Object, Object> valueMapEntry : valueMapEntries) {
                        Object mapKey = valueMapEntry.getKey();
                        Object mapValue = valueMapEntry.getValue();
                        if ((!StringUtils.isEmpty(mapKey)) && (!StringUtils.isEmpty(mapValue))) {
                            String stringValue = new StringBuffer().append(mapKey).append(valueAsMapDelimiter).append(mapValue).toString();
                            values.add(stringValue);
                        }
                    }
                }
                stringListMap.put(key, values);
            }
        }
        return stringListMap;
    }

}
