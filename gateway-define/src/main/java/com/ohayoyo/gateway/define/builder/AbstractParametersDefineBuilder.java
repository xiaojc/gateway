package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.core.ParametersDefine;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractParametersDefineBuilder<Define extends ParametersDefine, ThenDefineBuilder extends DefineBuilder> extends AbstractThenDefineBuilder<Define, ThenDefineBuilder> {

    private Set<Parameter> parameters;

    private Set<ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>>> parameterDefineBuilders = new HashSet<ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>>>();

    protected AbstractParametersDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

    @Override
    public final Define build() {
        if (!CollectionUtils.isEmpty(parameterDefineBuilders)) {
            for (ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> parameterDefineBuilder : parameterDefineBuilders) {
                parameter(parameterDefineBuilder.build());
            }
        }
        return this.buildDetails(parameters);
    }

    protected abstract Define buildDetails(Set<Parameter> parameters);

    public ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> parameter() {
        ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> parameterDefineBuilder = this.parameterBuilder();
        parameterDefineBuilders.add(parameterDefineBuilder);
        return parameterDefineBuilder;
    }

    private ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>> parameterBuilder() {
        return new ParameterDefineBuilder<AbstractParametersDefineBuilder<Define, ThenDefineBuilder>>(this);
    }

    public AbstractParametersDefineBuilder<Define, ThenDefineBuilder> parameters(Set<Parameter> parameters) {
        if (CollectionUtils.isEmpty(this.parameters)) {
            this.parameters = new HashSet<Parameter>();
        }
        if (!CollectionUtils.isEmpty(parameters)) {
            this.parameters.addAll(parameters);
        }
        return this;
    }

    public AbstractParametersDefineBuilder<Define, ThenDefineBuilder> parameter(Parameter parameter) {
        if (CollectionUtils.isEmpty(this.parameters)) {
            this.parameters = new HashSet<Parameter>();
        }
        this.parameters.add(parameter);
        return this;
    }

}
