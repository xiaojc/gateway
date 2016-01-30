package com.ohayoyo.gateway.define.core;

import java.io.Serializable;

public interface StatusDefine extends Serializable {

    String STANDARD = "standard";

    String CUSTOM = "custom";

    String getType();

    StatusDefine setType(String type);

    String getStatusCode();

    StatusDefine setStatusCode(String statusCode);

    String getReasonPhrase();

    StatusDefine setReasonPhrase(String reasonPhrase);

    String getErrorSolution();

    StatusDefine setErrorSolution(String errorSolution);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    String toString();
}
