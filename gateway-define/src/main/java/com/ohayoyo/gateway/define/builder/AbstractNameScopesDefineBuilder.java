package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.utils.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractNameScopesDefineBuilder<Define, ThenDefineBuilder extends DefineBuilder> extends AbstractThenDefineBuilder<Define, ThenDefineBuilder> {

    private String name;

    private Set<String> scopes;

    protected AbstractNameScopesDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

    public AbstractNameScopesDefineBuilder<Define, ThenDefineBuilder> name(String name) {
        this.name = name;
        return this;
    }

    public AbstractNameScopesDefineBuilder<Define, ThenDefineBuilder> scope(String scope) {
        if (CollectionUtils.isEmpty(this.scopes)) {
            this.scopes = new HashSet<String>();
        }
        this.scopes.add(scope);
        return this;
    }

    public AbstractNameScopesDefineBuilder<Define, ThenDefineBuilder> scopes(Set<String> scopes) {
        this.scopes = scopes;
        if (CollectionUtils.isEmpty(this.scopes)) {
            this.scopes = new HashSet<String>();
        }
        if (CollectionUtils.isNotEmpty(scopes)) {
            this.scopes.addAll(scopes);
        }
        return this;
    }

    @Override
    public final Define build() {
        return this.buildDetails(name, scopes);
    }

    protected abstract Define buildDetails(String name, Set<String> scopes);

}
