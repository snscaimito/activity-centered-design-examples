package net.caimito.conference.pages;

import static org.junit.Assert.assertTrue;
import net.caimito.conference.eventorganization.Event;
import net.caimito.conference.eventorganization.EventOrganizer;
import net.caimito.util.TestHelper;

import org.junit.Before;
import org.junit.Test;

public class BuyTicketTest {

	// Pages and services are pooled at runtime
	private BuyTicket buyTicketPage ;
	private EventOrganizer eventOrganizer ;
	
	@Before
	public void setup() {
		buyTicketPage = new BuyTicket() ;
		eventOrganizer = new EventOrganizer() ;
		
		// Pages and services are pooled at runtime
		TestHelper.set(buyTicketPage, "eventOrganizer", eventOrganizer) ;
	}
	
	@Test
	public void buyIt() {
		Event gizmoEvent = eventOrganizer.findEvent("Gizmo") ;
		TestHelper.set(buyTicketPage, 
			"attendee", "Stephan",
			"eventName", "Gizmo",
			"ticketTypeName", "Standard") ;
		
		buyTicketPage.onSuccess() ;
		
		assertTrue(eventOrganizer.hasValidTicket("Stephan", gizmoEvent)) ;
	}
	
}
