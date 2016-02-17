package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.ParameterDefine;
import com.ohayoyo.gateway.define.ParametersDefine;
import com.ohayoyo.gateway.define.utils.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractParametersDefineBuilder<Define extends ParametersDefine, ThenDefineBuilder extends DefineBuilder> extends AbstractThenDefineBuilder<Define, ThenDefineBuilder> {

    private Set<ParameterDefine> fields;

    private Set<FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>>> fieldDefineBuilders = new HashSet<FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>>>();

    protected AbstractParametersDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

    @Override
    public final Define build() {
        if (CollectionUtils.isNotEmpty(fieldDefineBuilders)) {
            for (FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> fieldDefineBuilder : fieldDefineBuilders) {
                field(fieldDefineBuilder.build());
            }
        }
        return this.buildDetails(fields);
    }

    protected abstract Define buildDetails(Set<ParameterDefine> fields);

    public FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> field() {
        FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> fieldDefineBuilder = this.fieldBuilder();
        fieldDefineBuilders.add(fieldDefineBuilder);
        return fieldDefineBuilder;
    }

    private FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> fieldBuilder() {
        return new FieldDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>>(this);
    }

    public AbstractParametersDefineBuilder<Define, ThenDefineBuilder> fields(Set<ParameterDefine> fields) {
        if (CollectionUtils.isEmpty(this.fields)) {
            this.fields = new HashSet<ParameterDefine>();
        }
        if (CollectionUtils.isNotEmpty(fields)) {
            this.fields.addAll(fields);
        }
        return this;
    }

    public AbstractParametersDefineBuilder<Define, ThenDefineBuilder> field(ParameterDefine field) {
        if (CollectionUtils.isEmpty(this.fields)) {
            this.fields = new HashSet<ParameterDefine>();
        }
        this.fields.add(field);
        return this;
    }

}
