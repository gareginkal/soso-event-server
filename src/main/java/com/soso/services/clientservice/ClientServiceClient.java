package com.soso.services.clientservice;

import com.soso.services.BaseRestClient;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */
public class ClientServiceClient extends BaseRestClient {
    public ClientServiceClient(@Value("${clientservice.id}") Integer serviceId,@Value("${appmode}") String appmode) {
        super(serviceId);
    }
}
