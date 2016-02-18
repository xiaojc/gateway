package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.core.Parameter;

/**
 * @author 蓝明乐
 */
public class ParameterDefineBuilder<ThenDefineBuilder extends DefineBuilder> extends AbstractThenDefineBuilder<Parameter, ThenDefineBuilder> {

    private String name;

    private String comment;

    private String overview;

    private String description;

    private String dataType;

    private Integer dataLength = -1;

    private Boolean nullable = true;

    private Object defaultValue;

    protected ParameterDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

    @Override
    public final Parameter build() {
        return this.buildDetails(name, comment, overview, description, dataType, dataLength, nullable, defaultValue);
    }

    public ParameterDefineBuilder<ThenDefineBuilder> name(String name) {
        this.name = name;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> comment(String comment) {
        this.comment = comment;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> overview(String overview) {
        this.overview = overview;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> description(String description) {
        this.description = description;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> dataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> nullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public ParameterDefineBuilder<ThenDefineBuilder> defaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    protected Parameter buildDetails(String name, String comment, String overview, String description, String dataType, Integer dataLength, Boolean nullable, Object defaultValue) {
        return new Parameter()
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
