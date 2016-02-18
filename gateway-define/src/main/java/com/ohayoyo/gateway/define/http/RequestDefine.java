package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.core.ObjectDefine;

import java.util.Set;

/**
 * <ul>
 * <li><a href="https://www.w3.org/TR/url/">标准</a></li>
 * <li><a href="http://baike.baidu.com/link?url=ia3S_WOT5YEHwRI2ZQ4d2_HqqKvAeNZHKmTskZBn3GlbNBMr2fAv9ZCjJfH3ZVfWrQnC9XiF4GN4NOG5rjlDca">百度</a></li>
 * </ul>
 *
 * @author 蓝明乐
 */
public interface RequestDefine extends ObjectDefine {

    Set<ProtocolDefine> getProtocols();

    RequestDefine setProtocols(Set<ProtocolDefine> protocols);

    Set<HostDefine> getHosts();

    RequestDefine setHosts(Set<HostDefine> hosts);

    PathDefine getPath();

    RequestDefine setPath(PathDefine path);

    QueriesDefine getQueries();

    RequestDefine setQueries(QueriesDefine queries);

    String getFragment();

    RequestDefine setFragment(String fragment);

    Set<MethodDefine> getMethods();

    RequestDefine setMethods(Set<MethodDefine> methods);

    HeadersDefine getHeaders();

    RequestDefine setHeaders(HeadersDefine headers);

    EntityDefine getEntity();

    RequestDefine setEntity(EntityDefine entity);

}
