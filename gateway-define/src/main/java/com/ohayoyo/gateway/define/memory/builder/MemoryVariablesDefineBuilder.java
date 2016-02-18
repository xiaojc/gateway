package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.PathDefineBuilder;
import com.ohayoyo.gateway.define.builder.VariablesDefineBuilder;
import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.http.VariablesDefine;
import com.ohayoyo.gateway.define.memory.http.MemoryVariablesDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryVariablesDefineBuilder extends VariablesDefineBuilder {

    protected MemoryVariablesDefineBuilder(PathDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected VariablesDefine buildDetails(Set<Parameter> parameters) {
        return (VariablesDefine) new MemoryVariablesDefine()
                .setParameters(parameters);
    }

}
