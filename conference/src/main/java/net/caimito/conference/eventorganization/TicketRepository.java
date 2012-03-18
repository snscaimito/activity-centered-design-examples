package net.caimito.conference.eventorganization;

import net.caimito.util.JSONFile;

import org.apache.tapestry5.json.JSONObject;

public class TicketRepository {

	public Ticket findByAttendeeAndEvent(String attendee, Event event) {
		JSONObject json = JSONFile.read(FileLocations.FILE_TICKETS_PURCHASED()) ;
		
		if (json == null)
			throw new TicketNotFoundException() ;
		
		if (json.has("attendee"))
			return new Ticket(json.getString("attendee"), new Event(json.getString("event")), new TicketType(json.getString("ticket_type"))) ;
		else
			throw new TicketNotFoundException() ;
	}

	public void createTicket(Event event, String attendee, TicketType ticketType) {
		JSONObject json = new JSONObject() ;
		json.put("attendee", attendee) ;
		json.put("event", event.getEventName()) ;
		json.put("ticket_type", ticketType.getTicketTypeName()) ;

		JSONFile.write(FileLocations.FILE_TICKETS_PURCHASED(), json) ;
	}

}
