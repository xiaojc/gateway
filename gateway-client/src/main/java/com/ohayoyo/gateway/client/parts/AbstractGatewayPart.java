package com.ohayoyo.gateway.client.parts;

import com.ohayoyo.gateway.client.core.AbstractGatewayScope;
import com.ohayoyo.gateway.client.core.GatewayPart;
import com.ohayoyo.gateway.client.utils.ReflectUtil;

public abstract class AbstractGatewayPart<T> extends AbstractGatewayScope implements GatewayPart<T> {

    private Class<?> type;

    public AbstractGatewayPart() {
        this.type = ReflectUtil.classGenericType(getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGatewayPart)) return false;
        AbstractGatewayPart<?> that = (AbstractGatewayPart<?>) o;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

}
