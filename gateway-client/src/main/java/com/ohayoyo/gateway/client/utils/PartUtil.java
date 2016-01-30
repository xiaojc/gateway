package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.client.core.*;
import com.ohayoyo.gateway.client.parts.AbstractGatewayPart;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Set;

public class PartUtil {

    public static <T> T newPartInstance(Set<Class<? extends GatewayPart<?>>> gatewayPartClasses, Class<T> gatewayPartType, Object... scopeParameters) throws GatewayException {
        T resultPart = null;
        GatewayPart<T> resultGatewayPart = null;
        Class<? extends GatewayPart<?>> resultGatewayPartClass = null;
        if (!CollectionUtils.isEmpty(gatewayPartClasses)) {
            for (Class<? extends GatewayPart<?>> gatewayPartClass : gatewayPartClasses) {
                Class<?> gatewayPartGenericType = ReflectUtil.classGenericType(gatewayPartClass);
                if (null != gatewayPartGenericType && (gatewayPartGenericType.equals(gatewayPartType))) {
                    resultGatewayPartClass = gatewayPartClass;
                    break;
                }
            }
        }
        if (null != resultGatewayPartClass) {
            try {
                resultGatewayPart = (GatewayPart<T>) resultGatewayPartClass.newInstance();
            } catch (Exception e) {
                throw new GatewayException(e);
            }
        }
        if (null != resultGatewayPart) {
            injectPartParameters(resultGatewayPart, scopeParameters);
            resultPart = resultGatewayPart.getPart();
        }
        return resultPart;
    }

    public static void injectPartParameters(GatewayPart<?> gatewayPart, Object... parameters) {
        if (!ObjectUtils.isEmpty(parameters)) {
            AbstractGatewayPart abstractGatewayPart;
            if (gatewayPart instanceof AbstractGatewayPart) {
                abstractGatewayPart = (AbstractGatewayPart) gatewayPart;
                for (Object parameter : parameters) {
                    if (parameter instanceof GatewayConfig) {
                        GatewayConfig gatewayConfig = (GatewayConfig) parameter;
                        abstractGatewayPart.setGatewayConfig(gatewayConfig);
                    } else if (parameter instanceof GatewayRequest) {
                        GatewayRequest gatewayRequest = (GatewayRequest) parameter;
                        abstractGatewayPart.setGatewayRequest(gatewayRequest);
                    } else if (parameter instanceof GatewayDefine) {
                        GatewayDefine gatewayDefine = (GatewayDefine) parameter;
                        abstractGatewayPart.setGatewayDefine(gatewayDefine);
                    } else if (parameter instanceof ClientHttpRequest) {
                        ClientHttpRequest clientHttpRequest = (ClientHttpRequest) parameter;
                        abstractGatewayPart.setClientHttpRequest(clientHttpRequest);
                    } else if (parameter instanceof ClientHttpResponse) {
                        ClientHttpResponse clientHttpResponse = (ClientHttpResponse) parameter;
                        abstractGatewayPart.setClientHttpResponse(clientHttpResponse);
                    }
                }
            }
        }
    }

}
