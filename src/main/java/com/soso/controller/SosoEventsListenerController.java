package com.soso.controller;

import com.soso.dto.EventDto;
import com.soso.services.EventListenerService;
import com.soso.utilities.JsonConverter;
import com.soso.utilities.JsonMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */

@RestController
@RequestMapping("eventsListener")
@CrossOrigin("*")
public class SosoEventsListenerController {


    @Autowired
    private EventListenerService eventListenerService;


    @RequestMapping(value = "/completedeventsbyclient/{clientId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getCompletedEventsByClientId(@PathVariable("clientId") Integer clientId, HttpServletResponse response) throws IOException {
        List<EventDto> eventDtos = eventListenerService.getCompletedEventsByClientId(clientId);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("events",eventDtos).build()));
    }

    @RequestMapping(value = "/acceptedeventsbyclient/{clientId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getAcceptedEventsByClientId(@PathVariable("clientId") Integer clientId,HttpServletResponse response) throws IOException {
        List<EventDto> eventDtos = eventListenerService.getAcceptedEventsByClientId(clientId);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("events",eventDtos).build()));
    }

    @RequestMapping(value = "/neweventsbypartner/{partnerId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getNewEventsByPartnerId(@PathVariable("partnerId") Integer partnerId,HttpServletResponse response) throws IOException {
        List<EventDto> eventDtos = eventListenerService.getNewEventsByPartnerId(partnerId);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("events",eventDtos).build()));

    }

    @RequestMapping(value = "/deleteneweventbypartner/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteNewEventByPartnerId(@PathVariable("id") Integer newEventId,HttpServletResponse response) throws IOException {
        Integer count = eventListenerService.deleteNewEventByEventId(newEventId);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("count",count).build()));
    }

    @RequestMapping(value = "/deleteacceptedeventbyclient/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAcceptedEventByClientId(@PathVariable("id") Integer newEventId, HttpServletResponse response) throws IOException {
        Integer count = eventListenerService.deleteAcceptedEventByClientId(newEventId);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("count",count).build()));
    }

    @RequestMapping(value = "/deletedoneeventbypartner/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDoneEventByClientId(@PathVariable("id") Integer newEventId,HttpServletResponse response) throws IOException{
        Integer count = eventListenerService.deleteDoneEventByClientId(newEventId);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("count",count).build()));
    }


    @RequestMapping(value = "/addneweventtopartner",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addEventToPartnerNewEvent(@RequestBody EventDto eventDto, HttpServletResponse httpServletResponse) throws IOException{
        Integer newEventId = eventListenerService.addEventToPartnerNewEvent(eventDto);
        httpServletResponse.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("newEventId",newEventId).build()));
    }

    @RequestMapping(value = "/adddoneeventtoclient",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addEventToClientDoneEvents(@RequestBody EventDto eventDto,HttpServletResponse response) throws IOException {
        Integer newEventId = eventListenerService.addEventToClientDoneEvents(eventDto);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("newEventId",newEventId).build()));
    }

    @RequestMapping(value = "/addacceptedeventtoclient",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addEventToClientAccepted(@RequestBody EventDto eventDto,HttpServletResponse response) throws IOException {
        Integer newEventId = eventListenerService.addEventToClientAccepted(eventDto);
        response.getWriter().write(JsonConverter.toJson(new JsonMapBuilder().add("newEventId",newEventId).build()));
    }


}
