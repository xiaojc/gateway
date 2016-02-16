package com.ohayoyo.gateway.client;

/**
 * @author 蓝明乐
 */
public interface GatewayValidator {

    boolean supports(Class<?> clazz);

    void validate(Object target) throws ValidatorException;

}
