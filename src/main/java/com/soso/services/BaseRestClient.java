package com.soso.services;

import com.soso.dto.ServiceInfoDto;
import com.soso.utilities.JsonConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by Garik Kalashyan on 3/12/2017.
 */
public class BaseRestClient {
    private RestTemplate restTemplate;
    private ServiceInfoDto destinationService;


    public BaseRestClient(@NotNull Integer serviceId){
        restTemplate = new RestTemplate();
        initializeBaseUrl(serviceId);
    }


    protected void initializeBaseUrl(Integer serviceId){
        String serviceDetailByIdJSONString =  new ServicesDetailService().getInfoByServiceId(serviceId);
        destinationService =  JsonConverter.getServiceInfoFromJSONString(serviceDetailByIdJSONString);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public ServiceInfoDto getDestinationService() {
        return destinationService;
    }





    private class ServicesDetailService {
        private String baseServicesDetailServiceUrl = "https://pure-badlands-72083.herokuapp.com/";

        private String getInfoByServiceId(Integer serviceId){
            return restTemplate.getForObject(baseServicesDetailServiceUrl + "serviceDetails/" + serviceId,String.class);
        }


    }

}
