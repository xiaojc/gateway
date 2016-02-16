package com.ohayoyo.gateway.define.http.builder;

/**
 * @author 蓝明乐
 */
public abstract class AbstractParentBuilder<Define, Reference extends DefineBuilder> implements DefineBuilder<Define>, ParentBuilder<Reference> {

    private Reference referenceBuilder;

    protected AbstractParentBuilder(Reference referenceBuilder) {
        this.referenceBuilder = referenceBuilder;
    }

    @Override
    public Reference parent() {
        return this.referenceBuilder;
    }

}
