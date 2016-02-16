package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.http.HostDefine;
import com.ohayoyo.gateway.define.http.builder.HostDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryHostDefine;

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
