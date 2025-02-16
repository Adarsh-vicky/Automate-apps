Feature: Login Naukri App

  Scenario: Login Naukri app with your credentials
    Given I launch naukri application
#    And I have logged into Naukri app with my credentials with email "xyz@gmail.com" and password "12345"
    And I have logged into Naukri app with my Google credentials with email "gn.star1960@gmail.com" and password "9658582344"
    When Validate my naukri page is displayed as "Ganapati nayak"
    Then click on my profile header "Ganapati nayak"
    And click on update profile
    And Select my location as "Pune" and save it
    And Verify the location updated as "Pune"
    And verify last updated shows as "Today"