Feature: Signup

  Scenario: Define ticket type
    When the event organizer defines a ticket type "Standard"
    And the event organizer sets the price for the "Standard" ticket to "20"
    Then a ticket of type "Standard" for "20" has been defined

  Scenario: As attendee I want to buy a ticket to the conference online so that I will be admitted without delay
    Given a ticket of type "General" for the price of "40" for the "Wizbang" conference has been defined
    When "Joe Fraser" buys a "General" ticket for the "Wizbang" conference
    Then a "General" ticket in the name of "Joe Fraser" for the "Wizbang" conference has been issued
