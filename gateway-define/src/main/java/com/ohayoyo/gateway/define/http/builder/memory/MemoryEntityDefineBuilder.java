package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.http.EntityDefine;
import com.ohayoyo.gateway.define.http.builder.DefineBuilder;
import com.ohayoyo.gateway.define.http.builder.EntityDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryEntityDefine;

/**
 * @author 蓝明乐
 */
public class MemoryEntityDefineBuilder<Reference extends DefineBuilder> extends EntityDefineBuilder<Reference> {

    protected MemoryEntityDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected EntityDefine buildDetails(String contentType, DataDefine entityData) {
        return new MemoryEntityDefine()
                .setContentType(contentType)
                .setEntityData(entityData);
    }

}
