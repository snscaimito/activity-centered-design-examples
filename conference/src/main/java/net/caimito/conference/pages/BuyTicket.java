package net.caimito.conference.pages;

import java.util.ArrayList;
import java.util.Collection;

import net.caimito.conference.eventorganization.Event;
import net.caimito.conference.eventorganization.EventOrganizer;
import net.caimito.conference.eventorganization.TicketType;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class BuyTicket {

	@Property
	private String attendee ;
	
	@Property
	private String eventName ;
	
	@Property
	private String ticketTypeName ;
	
	@Inject
	private EventOrganizer eventOrganizer ;
	
	public Collection<String> getEventNames() {
		Collection<String> eventNames = new ArrayList<String>() ;
		
		for (Event event : eventOrganizer.findAvailableEvents())
			eventNames.add(event.getEventName()) ;
		
		return eventNames ;
	}
	
	public void onSuccess() {
		Event event = eventOrganizer.findEvent(eventName) ;
		TicketType ticketType = new TicketType(ticketTypeName) ;
		eventOrganizer.buyTicket(event, attendee, ticketType) ;
	}
	
}
