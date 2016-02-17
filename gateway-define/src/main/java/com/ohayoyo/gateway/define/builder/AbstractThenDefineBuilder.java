package com.ohayoyo.gateway.define.builder;

/**
 * @author 蓝明乐
 */
public abstract class AbstractThenDefineBuilder<Define, ThenDefineBuilder extends DefineBuilder> implements DefineBuilder<Define>, com.ohayoyo.gateway.define.builder.ThenDefineBuilder<ThenDefineBuilder> {

    private ThenDefineBuilder then;

    protected AbstractThenDefineBuilder(ThenDefineBuilder then) {
        this.then = then;
    }

    @Override
    public ThenDefineBuilder then() {
        return this.then;
    }

}
