package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.RequestAutoFill;
import com.ohayoyo.gateway.define.FieldDefine;
import com.ohayoyo.gateway.define.http.PathDefine;
import com.ohayoyo.gateway.define.http.RequestDefine;
import com.ohayoyo.gateway.define.http.VariablesDefine;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RestfulRequestAutoFill implements RequestAutoFill {

    @Override
    public <RequestBody> void autoFill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest) {

        if (null == gatewayRequest) {
            return;
        }

        //request path variables

        PathDefine pathDefine = requestDefine.getPath();
        if (null != pathDefine) {
            VariablesDefine variablesDefine = pathDefine.getVariables();
            if (null != variablesDefine) {
                Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
                if (CollectionUtils.isEmpty(requestPathVariables)) {
                    requestPathVariables = new HashMap<String, String>();
                    gatewayRequest.setRequestPathVariables(requestPathVariables);
                }
                Set<FieldDefine> fieldDefines = variablesDefine.getFields();
            }
        }

        //

    }

}
