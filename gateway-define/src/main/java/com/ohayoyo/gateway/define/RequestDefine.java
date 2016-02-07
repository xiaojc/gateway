package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Set;

/**
 * 请求定义
 * <p>
 * 设计参照
 * <ul>
 * <li><a href="https://www.w3.org/TR/url/">标准</a></li>
 * <li><a href="http://baike.baidu.com/link?url=ia3S_WOT5YEHwRI2ZQ4d2_HqqKvAeNZHKmTskZBn3GlbNBMr2fAv9ZCjJfH3ZVfWrQnC9XiF4GN4NOG5rjlDca">百度</a></li>
 * </ul>
 * </p>
 */
public interface RequestDefine extends Serializable {

    Set<ProtocolDefine> getProtocols();

    RequestDefine setProtocols(Set<ProtocolDefine> protocols);

    Set<HostDefine> getHosts();

    RequestDefine setHosts(Set<HostDefine> hosts);

    PathDefine getPath();

    RequestDefine setPath(PathDefine path);

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
