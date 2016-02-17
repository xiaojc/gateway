package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.core.EntityDefine;

/**
 * @author 蓝明乐
 */
public abstract class EntityDefineBuilder<ThenDefineBuilder extends DefineBuilder> extends AbstractThenDefineBuilder<EntityDefine, ThenDefineBuilder> {

    private String contentType;

    private Object entityData;

    protected EntityDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

    public EntityDefineBuilder<ThenDefineBuilder> contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public EntityDefineBuilder<ThenDefineBuilder> entityData(Object entityData) {
        this.entityData = entityData;
        return this;
    }

    @Override
    public final EntityDefine build() {
        return this.buildDetails(contentType, entityData);
    }

    protected abstract EntityDefine buildDetails(String contentType, Object entityData);

}
