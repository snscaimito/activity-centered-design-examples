package net.caimito.conference.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

@IncludeStylesheet("context:layout/layout.css")
public class Layout {
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;
}
