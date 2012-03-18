package net.caimito.conference.eventorganization;

import net.caimito.conference.eventorganization.Event;
import net.caimito.conference.eventorganization.FileLocations;
import net.caimito.util.JSONFile;

import org.apache.tapestry5.json.JSONObject;

public class Fixtures {

	public static Event createEvent(String eventName) {
		JSONObject json = new JSONObject() ;
		json.put("event", eventName) ;
		JSONFile.write(FileLocations.FILE_TICKETS_DEFINED(), json) ;
		return new Event(eventName) ;
	}

	public static Ticket createTicket(String attendee, Event event, TicketType ticketType) {
		JSONObject json = new JSONObject() ;
		json.put("attendee", attendee) ;
		json.put("event", event.getEventName()) ;
		json.put("ticket_type", ticketType.getTicketTypeName()) ;
		JSONFile.write(FileLocations.FILE_TICKETS_PURCHASED(), json) ;
		return new Ticket(attendee, event, ticketType) ;
	}

	public static TicketType createTicketType(String ticketTypeName) {
		return new TicketType(ticketTypeName) ;
	}

}
