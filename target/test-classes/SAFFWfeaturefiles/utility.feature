Feature:

@Validatedetails
Scenario: Get a user
    Given url Baseurl
    And param id = Recordid
    And param page = Listpage
    When method Methods
    Then status 200