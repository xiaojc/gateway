package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.FieldDefine;
import com.ohayoyo.gateway.define.ReferenceDefine;
import com.ohayoyo.gateway.define.http.VariablesDefine;
import com.ohayoyo.gateway.define.http.builder.PathDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.VariablesDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryVariablesDefine;

import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryVariablesDefineBuilder extends VariablesDefineBuilder {

    protected MemoryVariablesDefineBuilder(PathDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected VariablesDefine buildDetails(String name, Set<FieldDefine> fields, Map<String, ReferenceDefine> references) {
        return (VariablesDefine) new MemoryVariablesDefine()
                .setName(name)
                .setFields(fields)
                .setReferences(references);
    }

}
