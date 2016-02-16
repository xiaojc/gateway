package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.EntityDefine;
import com.ohayoyo.gateway.define.http.HeadersDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;
import com.ohayoyo.gateway.define.http.StatusDefine;
import com.ohayoyo.gateway.define.utils.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class ResponseDefineBuilder extends AbstractParentBuilder<ResponseDefine, InterfaceDefineBuilder> {

    private Set<StatusDefine> statuses;

    private HeadersDefine headers;

    private EntityDefine entity;

    private Set<StatusDefineBuilder> statusDefineBuilders = new HashSet<StatusDefineBuilder>();

    private EntityDefineBuilder<ResponseDefineBuilder> entityDefineBuilder;

    protected ResponseDefineBuilder(InterfaceDefineBuilder interfaceDefineBuilder) {
        super(interfaceDefineBuilder);
    }

    @Override
    public final ResponseDefine build() {
        if (null != entityDefineBuilder) {
            entity(entityDefineBuilder.build());
        }
        if (ObjectUtils.isNotEmpty(statusDefineBuilders)) {
            for (StatusDefineBuilder statusDefineBuilder : statusDefineBuilders) {
                status(statusDefineBuilder.build());
            }
        }
        return this.buildDetails(statuses, headers, entity);
    }

    protected abstract ResponseDefine buildDetails(Set<StatusDefine> statuses, HeadersDefine headers, EntityDefine entity);

    public EntityDefineBuilder<ResponseDefineBuilder> entity() {
        entityDefineBuilder = this.entityBuilder();
        return entityDefineBuilder;
    }

    protected abstract EntityDefineBuilder<ResponseDefineBuilder> entityBuilder();

    public HeadersDefineBuilder<ResponseDefineBuilder> headers() {
        HeadersDefineBuilder<ResponseDefineBuilder> headersDefineBuilder = this.headersBuilder();
        this.headers(headersDefineBuilder.build());
        return headersDefineBuilder;
    }

    protected abstract HeadersDefineBuilder<ResponseDefineBuilder> headersBuilder();

    public StatusDefineBuilder status() {
        StatusDefineBuilder statusDefineBuilder = this.statusBuilder();
        statusDefineBuilders.add(statusDefineBuilder);
        return statusDefineBuilder;
    }

    protected abstract StatusDefineBuilder statusBuilder();

    public ResponseDefineBuilder entity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

    public ResponseDefineBuilder headers(HeadersDefine headers) {
        this.headers = headers;
        return this;
    }

    public ResponseDefineBuilder status(StatusDefine status) {
        if (ObjectUtils.isEmpty(this.statuses)) {
            this.statuses = new HashSet<StatusDefine>();
        }
        this.statuses.add(status);
        return this;
    }

    public ResponseDefineBuilder statuses(Set<StatusDefine> statuses) {
        this.statuses = statuses;
        if (ObjectUtils.isEmpty(this.statuses)) {
            this.statuses = new HashSet<StatusDefine>();
        }
        if (ObjectUtils.isNotEmpty(statuses)) {
            this.statuses.addAll(statuses);
        }
        return this;
    }

}
