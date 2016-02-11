package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

public interface StatusDefine extends ObjectDefine {

    String getStatusCode();

    StatusDefine setStatusCode(String statusCode);

    String getReasonPhrase();

    StatusDefine setReasonPhrase(String reasonPhrase);

    String getErrorSolution();

    StatusDefine setErrorSolution(String errorSolution);

}
