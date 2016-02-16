package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.http.EntityDefine;

/**
 * @author 蓝明乐
 */
public abstract class EntityDefineBuilder<Reference extends DefineBuilder> extends AbstractParentBuilder<EntityDefine, Reference> {

    private String contentType;

    private DataDefine entityData;

    protected EntityDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    public EntityDefineBuilder<Reference> contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public EntityDefineBuilder<Reference> entityData(DataDefine entityData) {
        this.entityData = entityData;
        return this;
    }

    @Override
    public final EntityDefine build() {
        return this.buildDetails(contentType, entityData);
    }

    protected abstract EntityDefine buildDetails(String contentType, DataDefine entityData);

}
