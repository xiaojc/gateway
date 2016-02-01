package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

/**
 * 响应定义
 */
public interface ResponseDefine extends Serializable {

    Set<StatusDefine> getStatuses();

    ResponseDefine setStatuses(Set<StatusDefine> statuses);

    Set<HeaderDefine> getHeaders();

    ResponseDefine setHeaders(Set<HeaderDefine> headers);

    EntityDefine getEntity();

    ResponseDefine setEntity(EntityDefine entity);

}
