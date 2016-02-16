package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.FieldDefine;

/**
 * @author 蓝明乐
 */
public abstract class FieldDefineBuilder<Reference extends DefineBuilder> extends AbstractParentBuilder<FieldDefine, Reference> {

    private String name;

    private String comment;

    private String overview;

    private String description;

    private String dataType;

    private Integer dataLength = -1;

    private Boolean nullable;

    private Object defaultValue;

    protected FieldDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    public final FieldDefine build() {
        return this.buildDetails(name, comment, overview, description, dataType, dataLength, nullable, defaultValue);
    }

    protected abstract FieldDefine buildDetails(String name, String comment, String overview, String description, String dataType, Integer dataLength, Boolean nullable, Object defaultValue);

    public FieldDefineBuilder<Reference> name(String name) {
        this.name = name;
        return this;
    }

    public FieldDefineBuilder<Reference> comment(String comment) {
        this.comment = comment;
        return this;
    }

    public FieldDefineBuilder<Reference> overview(String overview) {
        this.overview = overview;
        return this;
    }

    public FieldDefineBuilder<Reference> description(String description) {
        this.description = description;
        return this;
    }

    public FieldDefineBuilder<Reference> dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public FieldDefineBuilder<Reference> dataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    public FieldDefineBuilder<Reference> nullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public FieldDefineBuilder<Reference> defaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

}
