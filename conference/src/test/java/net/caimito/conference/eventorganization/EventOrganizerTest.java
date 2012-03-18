package net.caimito.conference.eventorganization;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.caimito.util.JSONFile;

import org.apache.tapestry5.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class EventOrganizerTest {

	@Before
	public void setup() {
		JSONFile.delete(FileLocations.FILE_TICKETS_DEFINED()) ;
		JSONFile.delete(FileLocations.FILE_TICKETS_PURCHASED()) ;
	}
	
	@Test
	public void noEventsAvailable() {
		EventOrganizer eventOrganizer = new EventOrganizer() ;
		
		assertNotNull(eventOrganizer.findAvailableEvents()) ;
		assertTrue(eventOrganizer.findAvailableEvents().isEmpty()) ;
	}
	
	@Test
	public void findGizmoEvent() {
		EventOrganizer eventOrganizer = new EventOrganizer() ;
		Event gizmoEvent = Fixtures.createEvent("Gizmo") ;
		
		assertTrue(eventOrganizer.findAvailableEvents().contains(gizmoEvent)) ;
	}

	@Test
	public void findGizmoEventByName() {
		EventOrganizer eventOrganizer = new EventOrganizer() ;
		Event gizmoEvent = Fixtures.createEvent("Gizmo") ;
		
		assertEquals(gizmoEvent, eventOrganizer.findEvent("Gizmo")) ;
	}

	@Test
	public void noValidTicket() {
		EventOrganizer eventOrganizer = new EventOrganizer() ;
		Event gizmoEvent = Fixtures.createEvent("Gizmo") ;
		
		assertFalse(eventOrganizer.hasValidTicket("Joe", gizmoEvent)) ;
	}
	
	@Test
	public void validTicketForJoeAtGizmoEvent() {
		EventOrganizer eventOrganizer = new EventOrganizer() ;
		Event gizmoEvent = Fixtures.createEvent("Gizmo") ;
		TicketType standardTicketType = Fixtures.createTicketType("Standard") ;
		Fixtures.createTicket("Joe", gizmoEvent, standardTicketType) ;
		
		assertTrue(eventOrganizer.hasValidTicket("Joe", gizmoEvent)) ;
	}
	
	@Test
	public void joeBuysStandardTicketForGizmoEvent() {
		EventOrganizer eventOrganizer = new EventOrganizer() ;
		Event gizmoEvent = Fixtures.createEvent("Gizmo") ;
		TicketType standardTicketType = Fixtures.createTicketType("Standard") ;

		eventOrganizer.buyTicket(gizmoEvent, "Joe", standardTicketType) ;
		assertTrue(eventOrganizer.hasValidTicket("Joe", gizmoEvent)) ;
	}
}
