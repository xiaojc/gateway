package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

/**
 * 请求定义
 */
//https://www.w3.org/TR/url/
//http://baike.baidu.com/link?url=ia3S_WOT5YEHwRI2ZQ4d2_HqqKvAeNZHKmTskZBn3GlbNBMr2fAv9ZCjJfH3ZVfWrQnC9XiF4GN4NOG5rjlDca
//http://golang.org/pkg/net/url/
public interface RequestDefine extends Serializable {

    Set<ProtocolDefine> getProtocols();

    RequestDefine setProtocols(Set<ProtocolDefine> protocols);

    @Deprecated
    Set<UserDefine> getUserDefines();

    @Deprecated
    RequestDefine setUserDefines(Set<UserDefine> userDefines);

    Set<HostDefine> getHosts();

    RequestDefine setHosts(Set<HostDefine> hosts);

    PathDefine getPath();

    RequestDefine setPath(PathDefine path);

    @Deprecated
    Set<ParameterDefine> getParameters();

    @Deprecated
    RequestDefine setParameters(Set<ParameterDefine> parameters);

    Set<QueryDefine> getQueries();

    RequestDefine setQueries(Set<QueryDefine> queries);

    String getFragment();

    RequestDefine setFragment(String fragment);

    Set<MethodDefine> getMethods();

    RequestDefine setMethods(Set<MethodDefine> methods);

    Set<HeaderDefine> getHeaders();

    RequestDefine setHeaders(Set<HeaderDefine> headers);

    EntityDefine getEntity();

    RequestDefine setEntity(EntityDefine entity);

}
