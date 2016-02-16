package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.utils.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractNameScopesDefineBuilder<Define, Reference extends DefineBuilder> extends AbstractParentBuilder<Define, Reference> {

    private String name;

    private Set<String> scopes;

    protected AbstractNameScopesDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    public AbstractNameScopesDefineBuilder<Define, Reference> name(String name) {
        this.name = name;
        return this;
    }

    public AbstractNameScopesDefineBuilder<Define, Reference> scope(String scope) {
        if (ObjectUtils.isEmpty(this.scopes)) {
            this.scopes = new HashSet<String>();
        }
        this.scopes.add(scope);
        return this;
    }

    public AbstractNameScopesDefineBuilder<Define, Reference> scopes(Set<String> scopes) {
        this.scopes = scopes;
        if (ObjectUtils.isEmpty(this.scopes)) {
            this.scopes = new HashSet<String>();
        }
        if (ObjectUtils.isNotEmpty(scopes)) {
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
