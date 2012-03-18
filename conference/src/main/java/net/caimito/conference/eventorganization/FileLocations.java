package net.caimito.conference.eventorganization;

public class FileLocations {
	
	public static String FILE_TICKETS_DEFINED() {
		return System.getenv("HOME") + "/tickets.json" ; 
	}
	
	public static String FILE_TICKETS_PURCHASED() {
		return System.getenv("HOME") + "/tickets_purchased.json" ;
	}
	
}
