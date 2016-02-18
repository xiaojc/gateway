package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.DefineBuilder;
import com.ohayoyo.gateway.define.builder.EntityDefineBuilder;
import com.ohayoyo.gateway.define.http.EntityDefine;
import com.ohayoyo.gateway.define.memory.http.MemoryEntityDefine;

/**
 * @author 蓝明乐
 */
public class MemoryEntityDefineBuilder<Reference extends DefineBuilder> extends EntityDefineBuilder<Reference> {

    protected MemoryEntityDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected EntityDefine buildDetails(String contentType, Object entityData) {
        return new MemoryEntityDefine()
                .setContentType(contentType)
                .setEntityData(entityData);
    }

}
