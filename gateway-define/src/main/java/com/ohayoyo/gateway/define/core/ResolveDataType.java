package com.ohayoyo.gateway.define.core;

/**
 * @author 蓝明乐
 */
public interface ResolveDataType {

    ResolveDataType DEFAULT = new DefineDataType();

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

    String OBJECT = "object";

    Class<?> resolve(String dataType, String referenceClass);

}
