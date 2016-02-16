package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.*;
import com.ohayoyo.gateway.http.HttpClientHandler;

public abstract class BehaviorGatewayClient extends AbstractGatewayClient {

    private DefineValidator defineValidator;

    private RequestAutoFill requestAutoFill;

    public BehaviorGatewayClient(HttpClientHandler httpClientHandler) {
        super(httpClientHandler);
        this.defineValidator = new RestfulDefineValidator();
        this.requestAutoFill = new RestfulRequestAutoFill();
    }

    public DefineValidator getDefineValidator() {
        return defineValidator;
    }

    public BehaviorGatewayClient setDefineValidator(DefineValidator defineValidator) {
        this.defineValidator = defineValidator;
        return this;
    }

    public RequestAutoFill getRequestAutoFill() {
        return requestAutoFill;
    }

    public BehaviorGatewayClient setRequestAutoFill(RequestAutoFill requestAutoFill) {
        this.requestAutoFill = requestAutoFill;
        return this;
    }

    public RestfulGatewayRequestBuilder newRestfulGatewayRequestBuilder() {
        return RestfulGatewayRequestBuilder.newInstance().conversionService(this.getHttpClientHandler().getConversionService());
    }

    public RestfulGatewayResponseBuilder newRestfulGatewayResponseBuilder() {
        return RestfulGatewayResponseBuilder.newInstance().conversionService(this.getHttpClientHandler().getConversionService());
    }

    @Override
    protected void defineVerify(GatewayDefine gatewayDefine) throws GatewayException {
        if (defineValidator.supports(gatewayDefine.getClass())) {
            defineValidator.validate(gatewayDefine);
        }
    }

    @Override
    protected <RequestBody> void requestFill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        requestAutoFill.autoFill(gatewayDefine.getRequest(), gatewayRequest);
    }

    @Override
    protected <RequestBody> void requestVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
    }

    @Override
    protected <ResponseBody> void resultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException {
    }

}
