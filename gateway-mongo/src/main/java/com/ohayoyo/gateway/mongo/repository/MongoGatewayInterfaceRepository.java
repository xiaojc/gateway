package com.ohayoyo.gateway.mongo.repository;

import com.ohayoyo.gateway.mongo.model.MongoGatewayInterface;
import com.ohayoyo.spring.audit.mongo.repository.MongoAuditRepository;

public interface MongoGatewayInterfaceRepository extends MongoAuditRepository<MongoGatewayInterface> {

    MongoGatewayInterface findByKey(String key);

}
