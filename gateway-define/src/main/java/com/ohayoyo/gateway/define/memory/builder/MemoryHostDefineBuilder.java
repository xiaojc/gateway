package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.core.HostDefine;
import com.ohayoyo.gateway.define.builder.HostDefineBuilder;
import com.ohayoyo.gateway.define.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.memory.http.MemoryHostDefine;

/**
 * @author 蓝明乐
 */
public class MemoryHostDefineBuilder extends HostDefineBuilder {

    protected MemoryHostDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

    @Override
    protected HostDefine buildDetails(String hostname, Integer port, String scope) {
        return new MemoryHostDefine()
                .setHostname(hostname)
                .setPort(port)
                .setScope(scope);
    }

}
