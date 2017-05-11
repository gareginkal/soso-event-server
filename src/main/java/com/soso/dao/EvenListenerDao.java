package com.soso.dao;

import com.soso.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */

@Repository
public class EvenListenerDao {
    private static final String GET_ACCEPTED_EVENTS_BY_CLIENT = "SELECT * FROM public.clientacceptedrequests WHERE clientid = :clientid";
    private static final String GET_COMPLETED_EVENTS_BY_CLIENT = "SELECT * FROM public.clientdonerequests WHERE clientid = :clientid";
    private static final String GET_PARTNER_NEW_EVENTS_BY_PARTNER = "SELECT * FROM public.partnernewrequests WHERE partnerid = :partnerid";

    private static final String DELETE_PARTNER_NEW_EVENTS_BY_ID = "DELETE FROM public.partnernewrequests WHERE id = :id";
    private static final String DELETE_CLIENT_ACCEPTED_EVENTS_BY_ID = "DELETE FROM public.clientacceptedrequests WHERE id = :id";
    private static final String DELETE_CLIENT_DONE_EVENTS_BY_ID = "DELETE FROM public.clientdonerequests WHERE id = :id";



    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    public NamedParameterJdbcOperations getNamedParameterJdbcOperations() {
        return namedParameterJdbcOperations;
    }

    public List<EventDto> getCompletedEventsByClientId(Integer clientId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("clientid",clientId);
        return getNamedParameterJdbcOperations().query(GET_COMPLETED_EVENTS_BY_CLIENT,paramMap,new BeanPropertyRowMapper<>(EventDto.class));
    }

    public List<EventDto> getAcceptedEventsByClientId(Integer clientId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("clientid",clientId);
        return getNamedParameterJdbcOperations().query(GET_ACCEPTED_EVENTS_BY_CLIENT,paramMap,new BeanPropertyRowMapper<>(EventDto.class));
    }

    public List<EventDto> getNewEventsByPartnerId(Integer partnerId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("partnerid",partnerId);
        return getNamedParameterJdbcOperations().query(GET_PARTNER_NEW_EVENTS_BY_PARTNER,paramMap,new BeanPropertyRowMapper<>(EventDto.class));
    }

    public Integer deleteNewEventByEventId(Integer newEventId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",newEventId);
        return getNamedParameterJdbcOperations().update(DELETE_PARTNER_NEW_EVENTS_BY_ID,paramMap);
    }

    public Integer deleteAcceptedEventByClientId(Integer newEventId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",newEventId);
        return getNamedParameterJdbcOperations().update(DELETE_CLIENT_ACCEPTED_EVENTS_BY_ID,paramMap);
    }

    public Integer deleteDoneEventByClientId(Integer newEventId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",newEventId);
        return getNamedParameterJdbcOperations().update(DELETE_CLIENT_DONE_EVENTS_BY_ID,paramMap);
    }

    public Integer addEventToPartnerNewEvent(EventDto eventDto){
        String addQuery = "SELECT addeventintopartnernewrequests (:_clientid, :_partnerid, :_requestid, :_isseen, :_seentime)";

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("_clientid",eventDto.getClientId());
        paramMap.put("_partnerid",eventDto.getPartnerId());
        paramMap.put("_requestid",eventDto.getRequestId());
        paramMap.put("_isseen",eventDto.isSeen());
        paramMap.put("_seentime",eventDto.getSeenTime());

        return getNamedParameterJdbcOperations().queryForObject(addQuery,paramMap,Integer.class);
    }

     public Integer addEventToClientDoneEvents(EventDto eventDto){
        String addQuery = "SELECT addeventintoclientdonerequests ( :_clientid, :_partnerid, :_requestid, :_isseen)";

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("_clientid",eventDto.getClientId());
        paramMap.put("_partnerid",eventDto.getPartnerId());
        paramMap.put("_requestid",eventDto.getRequestId());
        paramMap.put("_isseen",eventDto.isSeen());

        return getNamedParameterJdbcOperations().queryForObject(addQuery,paramMap,Integer.class);
    }

    public Integer addEventToClientAccepted(EventDto eventDto){
        String addQuery = "SELECT addeventintoclientaccepted ( :_clientid, :_partnerid, :_requestid)";

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("_clientid",eventDto.getClientId());
        paramMap.put("_partnerid",eventDto.getPartnerId());
        paramMap.put("_requestid",eventDto.getRequestId());

        return getNamedParameterJdbcOperations().queryForObject(addQuery,paramMap,Integer.class);
    }








}
