package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.http.PathDefine;
import com.ohayoyo.gateway.define.http.VariablesDefine;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class PathDefineBuilder extends AbstractThenDefineBuilder<PathDefine, RequestDefineBuilder> {

    private String project;

    private String module;

    private String operate;

    private String resource;

    private VariablesDefine variables;

    private VariablesDefineBuilder variablesDefineBuilder;

    protected PathDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

    @Override
    public final PathDefine build() {
        if (!ObjectUtils.isEmpty(variablesDefineBuilder)) {
            variables(variablesDefineBuilder.build());
        }
        return this.buildDetails(project, module, operate, resource, variables);
    }

    protected abstract PathDefine buildDetails(String project, String module, String operate, String resource, VariablesDefine variables);

    public VariablesDefineBuilder variables() {
        variablesDefineBuilder = this.variablesBuilder();
        return variablesDefineBuilder;
    }

    protected abstract VariablesDefineBuilder variablesBuilder();

    public PathDefineBuilder project(String project) {
        this.project = project;
        return this;
    }

    public PathDefineBuilder module(String module) {
        this.module = module;
        return this;
    }

    public PathDefineBuilder operate(String operate) {
        this.operate = operate;
        return this;
    }

    public PathDefineBuilder resource(String resource) {
        this.resource = resource;
        return this;
    }

    public PathDefineBuilder variables(VariablesDefine variables) {
        this.variables = variables;
        return this;
    }

}
