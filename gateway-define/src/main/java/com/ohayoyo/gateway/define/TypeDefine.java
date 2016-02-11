package com.ohayoyo.gateway.define;

public class TypeDefine implements ObjectDefine {

    //primitive

    public static String DATA_TYPE_BYTE = "BYTE";

    public static TypeDefine BYTE = new TypeDefine(DATA_TYPE_BYTE, true, false, false, false, false, false, false);

    public static String DATA_TYPE_SHORT = "SHORT";

    public static TypeDefine SHORT = new TypeDefine(DATA_TYPE_SHORT, true, false, false, false, false, false, false);

    public static String DATA_TYPE_INT = "INT";

    public static TypeDefine INT = new TypeDefine(DATA_TYPE_INT, true, false, false, false, false, false, false);

    public static String DATA_TYPE_LONG = "LONG";

    public static TypeDefine LONG = new TypeDefine(DATA_TYPE_LONG, true, false, false, false, false, false, false);

    public static String DATA_TYPE_CHAR = "CHAR";

    public static TypeDefine CHAR = new TypeDefine(DATA_TYPE_CHAR, true, false, false, false, false, false, false);

    public static String DATA_TYPE_FLOAT = "FLOAT";

    public static TypeDefine FLOAT = new TypeDefine(DATA_TYPE_FLOAT, true, false, false, false, false, false, false);

    public static String DATA_TYPE_DOUBLE = "DOUBLE";

    public static TypeDefine DOUBLE = new TypeDefine(DATA_TYPE_DOUBLE, true, false, false, false, false, false, false);

    public static String DATA_TYPE_BOOLEAN = "BOOLEAN";

    public static TypeDefine BOOLEAN = new TypeDefine(DATA_TYPE_BOOLEAN, true, false, false, false, false, false, false);

    // object

    public static String DATA_TYPE_STRING = "STRING";

    public static TypeDefine STRING = new TypeDefine(DATA_TYPE_STRING, false, true, false, false, false, false, false);

    public static String DATA_TYPE_FILE = "FILE";

    public static TypeDefine FILE = new TypeDefine(DATA_TYPE_FILE, false, true, false, false, false, false, false);

    //stream

    public static String DATA_TYPE_STREAM = "STREAM";

    public static TypeDefine STREAM = new TypeDefine(DATA_TYPE_STREAM, false, false, true, false, false, false, false);

    //pojo

    public static String DATA_TYPE_POJO = "POJO";

    public static TypeDefine POJO = new TypeDefine(DATA_TYPE_POJO, false, false, false, true, false, false, false);

    //arrays

    public static String DATA_TYPE_ARRAYS = "ARRAYS";

    public static TypeDefine ARRAYS = new TypeDefine(DATA_TYPE_ARRAYS, false, false, false, false, true, false, false);

    //collection

    public static String DATA_TYPE_COLLECTION = "COLLECTION";

    public static TypeDefine COLLECTION = new TypeDefine(DATA_TYPE_COLLECTION, false, false, false, false, false, true, false);

    //map

    public static String DATA_TYPE_MAP = "MAP";

    public static TypeDefine MAP = new TypeDefine(DATA_TYPE_MAP, false, false, false, false, false, false, true);

    private String name;

    private Boolean primitiveType;

    private Boolean objectType;

    private Boolean streamType;

    private Boolean classType;

    private Boolean arraysType;

    private Boolean collectionType;

    private Boolean mapType;

    public TypeDefine() {
    }

    public TypeDefine(String name, Boolean primitiveType, Boolean objectType, Boolean streamType, Boolean classType, Boolean arraysType, Boolean collectionType, Boolean mapType) {
        this.name = name;
        this.primitiveType = primitiveType;
        this.objectType = objectType;
        this.streamType = streamType;
        this.classType = classType;
        this.arraysType = arraysType;
        this.collectionType = collectionType;
        this.mapType = mapType;
    }

    public String getName() {
        return name;
    }

    public TypeDefine setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getPrimitiveType() {
        return primitiveType;
    }

    public TypeDefine setPrimitiveType(Boolean primitiveType) {
        this.primitiveType = primitiveType;
        return this;
    }

    public Boolean getObjectType() {
        return objectType;
    }

    public TypeDefine setObjectType(Boolean objectType) {
        this.objectType = objectType;
        return this;
    }

    public Boolean getStreamType() {
        return streamType;
    }

    public TypeDefine setStreamType(Boolean streamType) {
        this.streamType = streamType;
        return this;
    }

    public Boolean getClassType() {
        return classType;
    }

    public TypeDefine setClassType(Boolean classType) {
        this.classType = classType;
        return this;
    }

    public Boolean getArraysType() {
        return arraysType;
    }

    public TypeDefine setArraysType(Boolean arraysType) {
        this.arraysType = arraysType;
        return this;
    }

    public Boolean getCollectionType() {
        return collectionType;
    }

    public TypeDefine setCollectionType(Boolean collectionType) {
        this.collectionType = collectionType;
        return this;
    }

    public Boolean getMapType() {
        return mapType;
    }

    public TypeDefine setMapType(Boolean mapType) {
        this.mapType = mapType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeDefine)) return false;
        TypeDefine that = (TypeDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
