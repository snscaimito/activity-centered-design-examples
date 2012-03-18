package net.caimito.conference.eventorganization;

import java.util.ArrayList;
import java.util.Collection;

import net.caimito.util.JSONFile;

import org.apache.tapestry5.json.JSONObject;

public class EventRepository {

	public Collection<Event> findAll() {
		Collection<Event> events = new ArrayList<Event>();

		JSONObject json = JSONFile.read(FileLocations.FILE_TICKETS_DEFINED());

		if (json.has("event"))
			events.add(new Event(json.getString("event"))) ;
		
		return events;
	}
	
	public Event findEvent(String eventName) {
		JSONObject json = JSONFile.read(FileLocations.FILE_TICKETS_DEFINED());

		if (json.has("event") && json.getString("event").equals(eventName))
			return new Event(json.getString("event")) ;
		else
			throw new RuntimeException(String.format("Can't find %s", eventName)) ;
	}

	
}
