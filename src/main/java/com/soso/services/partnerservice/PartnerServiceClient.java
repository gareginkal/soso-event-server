package com.soso.services.partnerservice;

import com.soso.services.BaseRestClient;

import javax.validation.constraints.NotNull;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */
public class PartnerServiceClient extends BaseRestClient {
    public PartnerServiceClient(@NotNull Integer serviceId) {
        super(serviceId);
    }

    public String getAutocompletedEvents(){
        return getRestTemplate().getForObject(getDestinationService().getUrl()+"partner/getautocompletedrequests",String.class);
    }

}
