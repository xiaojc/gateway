package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.http.PathDefine;
import com.ohayoyo.gateway.define.http.VariablesDefine;
import com.ohayoyo.gateway.define.http.builder.PathDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.VariablesDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryPathDefine;

/**
 * @author 蓝明乐
 */
public class MemoryPathDefineBuilder extends PathDefineBuilder {

    protected MemoryPathDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

    @Override
    protected PathDefine buildDetails(String project, String module, String operate, String resource, VariablesDefine variables) {
        return new MemoryPathDefine()
                .setProject(project)
                .setModule(module)
                .setOperate(operate)
                .setResource(resource)
                .setVariables(variables);
    }

    @Override
    protected VariablesDefineBuilder variablesBuilder() {
        return new MemoryVariablesDefineBuilder(this);
    }
}
