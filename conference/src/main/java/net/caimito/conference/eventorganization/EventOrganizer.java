package net.caimito.conference.eventorganization;

import java.util.Collection;

public class EventOrganizer {

	private TicketRepository ticketRepository = new TicketRepository() ;
	private EventRepository eventRepository = new EventRepository() ;

	public boolean hasValidTicket(String attendee, Event event) {
		try {
			ticketRepository.findByAttendeeAndEvent(attendee, event) ;
			return true ;
		} catch (TicketNotFoundException e) {
			return false ;
		}
	}

	public void buyTicket(Event event, String attendee, TicketType ticketType) {
		ticketRepository.createTicket(event, attendee, ticketType) ;
	}

	public Collection<Event> findAvailableEvents() {
		return eventRepository.findAll() ;
	}

	public Event findEvent(String eventName) {
		return eventRepository.findEvent(eventName) ;
	}

}
