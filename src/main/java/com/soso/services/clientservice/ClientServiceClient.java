package com.soso.services.clientservice;

import com.soso.services.BaseRestClient;

import javax.validation.constraints.NotNull;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */
public class ClientServiceClient extends BaseRestClient {
    public ClientServiceClient(@NotNull Integer serviceId) {
        super(serviceId);
    }
}
