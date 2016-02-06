package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Set;

public interface ResponseDefine extends Serializable {

    Set<StatusDefine> getStatuses();

    ResponseDefine setStatuses(Set<StatusDefine> statuses);

    Set<HeaderDefine> getHeaders();

    ResponseDefine setHeaders(Set<HeaderDefine> headers);

    EntityDefine getEntity();

    ResponseDefine setEntity(EntityDefine entity);

}
