package net.caimito.conference.eventorganization;

public class Ticket {

	private String attendee ;
	private Event event ;
	private TicketType ticketType ;
	
	public Ticket(String attendee, Event event, TicketType ticketType) {
		this.attendee = attendee ;
		this.event = event ;
		this.ticketType = ticketType ;
	}

}
