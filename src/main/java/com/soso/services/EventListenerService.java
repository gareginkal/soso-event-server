package com.soso.services;

import com.soso.dao.EvenListenerDao;
import com.soso.dto.EventDto;
import com.soso.utilities.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */

@Repository
@Configurable
public class EventListenerService extends BaseRestClient {


    private static EvenListenerDao evenListenerDaoStatic;

    @Autowired
    private EvenListenerDao evenListenerDao;

    @Autowired
    public EventListenerService(@Value("${eventservice.id}") Integer defaultId){
        super(defaultId);
    }

    @PostConstruct
    private void initStaticDao() {
        if (evenListenerDaoStatic == null) {
            evenListenerDaoStatic = this.evenListenerDao;
        }
    }

    public List<EventDto> getCompletedEventsByClientId(Integer clientId) {
        return evenListenerDao.getCompletedEventsByClientId(clientId);
    }

    public List<EventDto> getAcceptedEventsByClientId(Integer clientId) {
        return evenListenerDao.getAcceptedEventsByClientId(clientId);
    }

    public List<EventDto> getNewEventsByPartnerId(Integer partnerId) {
        return evenListenerDao.getNewEventsByPartnerId(partnerId);
    }

    public Integer deleteNewEventByEventId(Integer newEventId) {
        return evenListenerDao.deleteNewEventByEventId(newEventId);
    }

    public Integer deleteAcceptedEventByClientId(Integer newEventId) {
        return evenListenerDao.deleteAcceptedEventByClientId(newEventId);
    }

    public Integer deleteDoneEventByClientId(Integer newEventId) {
        return evenListenerDao.deleteDoneEventByClientId(newEventId);
    }

    public Integer addEventToPartnerNewEvent(EventDto eventDto) {
        return evenListenerDao.addEventToPartnerNewEvent(eventDto);
    }

    public Integer addEventToClientDoneEvents(EventDto eventDto) {
        return evenListenerDaoStatic.addEventToClientDoneEvents(eventDto);
    }

    public Integer addEventToClientAccepted(EventDto eventDto) {
        return evenListenerDao.addEventToClientAccepted(eventDto);
    }

    public void autoAddCompletedEvents(String json) {
        List<EventDto> eventDtos = JsonConverter.getEventsFromJSONString(json);
        for (EventDto eventDto : eventDtos) {
            addEventToClientDoneEvents(eventDto);
        }
    }

}
