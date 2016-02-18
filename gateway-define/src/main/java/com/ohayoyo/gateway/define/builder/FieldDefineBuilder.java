package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.ParameterDefine;

/**
 * @author 蓝明乐
 */
public class FieldDefineBuilder<ThenDefineBuilder extends DefineBuilder> extends AbstractThenDefineBuilder<ParameterDefine, ThenDefineBuilder> {

    private String name;

    private String comment;

    private String overview;

    private String description;

    private String dataType;

    private Integer dataLength = -1;

    private Boolean nullable = true;

    private Object defaultValue;

    protected FieldDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

    @Override
    public final ParameterDefine build() {
        return this.buildDetails(name, comment, overview, description, dataType, dataLength, nullable, defaultValue);
    }

    public FieldDefineBuilder<ThenDefineBuilder> name(String name) {
        this.name = name;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> comment(String comment) {
        this.comment = comment;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> overview(String overview) {
        this.overview = overview;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> description(String description) {
        this.description = description;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> dataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> nullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public FieldDefineBuilder<ThenDefineBuilder> defaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    protected ParameterDefine buildDetails(String name, String comment, String overview, String description, String dataType, Integer dataLength, Boolean nullable, Object defaultValue) {
        return new ParameterDefine()
                .setName(name)
                .setComment(comment)
                .setDataLength(dataLength)
                .setDataType(dataType)
                .setDefaultValue(defaultValue)
                .setDescription(description)
                .setNullable(nullable)
                .setOverview(overview);
    }


}
