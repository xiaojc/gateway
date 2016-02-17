package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.exception.ValidatorException;

/**
 * @author 蓝明乐
 */
public interface GatewayValidator {

    boolean supports(Class<?> supportType);

    void validate(Object target) throws ValidatorException;

}
