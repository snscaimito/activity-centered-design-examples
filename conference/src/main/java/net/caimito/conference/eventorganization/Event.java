package net.caimito.conference.eventorganization;

public class Event {

	private String eventName ;
	
	protected Event(String eventName) {
		this.eventName = eventName ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Event) {
			Event other = (Event) obj ;
			return eventName.equals(other.eventName) ;
		} else
			return false ;
	}
	
	@Override
	public int hashCode() {
		return eventName.hashCode() ;
	}

	public String getEventName() {
		return eventName ;
	}

}
