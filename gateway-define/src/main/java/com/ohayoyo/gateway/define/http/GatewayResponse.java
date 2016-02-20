package com.ohayoyo.gateway.define.http;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface GatewayResponse<Status extends GatewayStatus, Headers extends GatewayHeaders, Entity extends GatewayEntity> {

    Set<Status> getStatuses();

    void setStatuses(Set<Status> statuses);

    Headers getHeaders();

    void setHeaders(Headers headers);

    Entity getEntity();

    void setEntity(Entity entity);

}
