package com.soso;

import com.soso.controller.SosoEventsListenerController;
import com.soso.dto.EventDto;
import com.soso.services.EventListenerService;
import com.soso.services.partnerservice.PartnerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class SosoEventListenerServiceApplication {

	@Autowired
	private  EventListenerService eventListenerService;

	public static void main(String[] args) {
		SpringApplication.run(SosoEventListenerServiceApplication.class, args);
		Timer timer = new Timer();
		timer.schedule(new AutoProcessesHandler(), 0, 10*1000);
	}

	static class AutoProcessesHandler extends TimerTask {
		private final PartnerServiceClient partnerServiceClient =  new PartnerServiceClient(2);



		public void run() {
			try{
			   new EventListenerService(6).autoAddCompletedEvents(partnerServiceClient.getAutocompletedEvents());
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
