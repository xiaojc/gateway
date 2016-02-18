package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.util.ObjectUtils;

public class ClientAutofill implements GatewayAutofill {

    @Override
    public <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest) {

        if (ObjectUtils.isEmpty(gatewayRequest)) {
            return;
        }

        //request path variables

//        PathDefine pathDefine = requestDefine.getPath();
//        if (null != pathDefine) {
//            VariablesDefine variablesDefine = pathDefine.getVariables();
//            if (null != variablesDefine) {
//                Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
//                if (CollectionUtils.isEmpty(requestPathVariables)) {
//                    requestPathVariables = new HashMap<String, String>();
//                    gatewayRequest.setRequestPathVariables(requestPathVariables);
//                }
//                Set<ParameterDefine> parameterDefines = variablesDefine.getFields();
//            }
//        }

        //

    }

}
