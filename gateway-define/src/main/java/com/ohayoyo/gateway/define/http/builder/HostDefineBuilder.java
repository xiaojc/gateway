package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.HostDefine;

/**
 * @author 蓝明乐
 */
public abstract class HostDefineBuilder extends AbstractParentBuilder<HostDefine, RequestDefineBuilder> {

    private String hostname;

    private Integer port;

    private String scope;

    protected HostDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

    public HostDefineBuilder hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public HostDefineBuilder port(Integer port) {
        this.port = port;
        return this;
    }

    public HostDefineBuilder scope(String scope) {
        this.scope = scope;
        return this;
    }

    @Override
    public final HostDefine build() {
        return this.buildDetails(hostname, port, scope);
    }

    protected abstract HostDefine buildDetails(String hostname, Integer port, String scope);

}
