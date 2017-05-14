package com.soso.services.partnerservice;

import com.soso.services.BaseRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */

public class PartnerServiceClient extends BaseRestClient {

    @Autowired
    public PartnerServiceClient(@Value("${partnerservice.id}") Integer serviceId) {
        super(serviceId);
    }

    public String getAutocompletedEvents(){
        return getRestTemplate().getForObject(getDestinationService().getUrl()+"partner/getautocompletedrequests",String.class);
    }

}
