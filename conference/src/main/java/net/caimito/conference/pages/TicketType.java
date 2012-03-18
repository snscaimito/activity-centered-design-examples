package net.caimito.conference.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;

public class TicketType {
	@Property
	private String ticketType ;
	
	@Property
	private String ticketPrice ;
	
	public Object onSuccess() {
		JSONObject json = new JSONObject() ;
		json.put("ticket_type", ticketType) ;
		json.put("ticket_price", ticketPrice) ;
		
		try {
			FileWriter writer = new FileWriter(System.getenv("HOME") + File.separator + "tickets.json");
			writer.write(json.toString()) ;
			writer.close() ;
		} catch (IOException e) {
			throw new RuntimeException(e) ;
		}
		
		return null ;
	}

}
