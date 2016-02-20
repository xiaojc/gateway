package com.ohayoyo.gateway.define.resolver;

import com.ohayoyo.gateway.define.core.GatewayType;

/**
 * @author 蓝明乐
 */
public interface GatewayTypeResolver<Type extends GatewayType> {

    String BYTE = "byte";

    String SHORT = "short";

    String INT = "int";

    String LONG = "long";

    String CHAR = "char";

    String FLOAT = "float";

    String DOUBLE = "double";

    String BOOLEAN = "boolean";

    String LIST = "list";

    String SET = "set";

    String MAP = "map";

    String STRING = "string";

    String FILE = "file";

    Class<?> resolve(Type type);

}
