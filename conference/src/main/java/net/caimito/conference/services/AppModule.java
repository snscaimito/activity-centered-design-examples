package net.caimito.conference.services;

import net.caimito.conference.eventorganization.EventOrganizer;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

public class AppModule {
	
	public static void bind(ServiceBinder binder) {
		binder.bind(EventOrganizer.class) ;
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
	}

}
