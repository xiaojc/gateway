package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

import java.util.Set;

public interface ResponseDefine extends ObjectDefine {

    Set<StatusDefine> getStatuses();

    ResponseDefine setStatuses(Set<StatusDefine> statuses);

    HeadersDefine getHeaders();

    ResponseDefine setHeaders(HeadersDefine headers);

    EntityDefine getEntity();

    ResponseDefine setEntity(EntityDefine entity);

}
