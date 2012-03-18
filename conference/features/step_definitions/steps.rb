When /^the event organizer defines a ticket type "([^"]*)"$/ do |ticket_type|
  @browser.goto BASE_URL + "/tickettype"
  @browser.text_field(:id, "ticketType").set(ticket_type)
end

When /^the event organizer sets the price for the "([^"]*)" ticket to "([^"]*)"$/ do |ticket_type, ticket_price|
  @browser.text_field(:id, "ticketPrice").set(ticket_price)
  @browser.button(:id, "submit").click
end

Then /^a ticket of type "([^"]*)" for "([^"]*)" has been defined$/ do |ticket_type, ticket_price|
  File.open(FILE_TICKETS_DEFINED, 'r') do |file|
    document = JSON.parse file.read
    fail "Ticket type was #{document['ticket_type']} expected #{ticket_type}" unless ticket_type.eql? document['ticket_type']
    fail "Ticket price was #{document['ticket_price']} expected #{ticket_price}" unless ticket_price.eql? document['ticket_price']
  end
end

Given /^a ticket of type "([^"]*)" for the price of "([^"]*)" for the "([^"]*)" conference has been defined$/ do |ticket_type, ticket_price, event|
  document = JSON 'ticket_type' => ticket_type, 'ticket_price' => ticket_price, 'event' => event
  File.open(FILE_TICKETS_DEFINED, 'w' ) do |out|
    out.write document
  end
end

When /^"([^"]*)" buys a "([^"]*)" ticket for the "([^"]*)" conference$/ do |attendee, ticket_type, event|
  FileUtils.rm FILE_TICKETS_PURCHASED unless File.exists? FILE_TICKETS_DEFINED
  @browser.goto BASE_URL + "/buyticket"
  @browser.text_field(:id, "attendee").set(attendee)
  @browser.select_list(:id, "events").select(event)
  @browser.select_list(:id, "ticketType").select(ticket_type)
  @browser.button(:id, "submit").click
end

Then /^a "([^"]*)" ticket in the name of "([^"]*)" for the "([^"]*)" conference has been issued$/ do |ticket_type, attendee, event|
  File.open(FILE_TICKETS_PURCHASED, 'r') do |file|
    document = JSON.parse file.read
    fail "No ticket purchase for ticket type #{ticket_type}" unless ticket_type.eql? document['ticket_type']
    fail "No ticket purchase for attendee #{attendee}" unless attendee.eql? document['attendee']
    fail "No ticket purchase for event #{event}" unless event.eql? document['event']
  end
end
