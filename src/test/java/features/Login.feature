# Login functionality feature

Feature: Login functionality feature
  @login
  Scenario: TC_001 Validate that user can login using the valid login credentials
    Given user is on application login page
    When user enters username "Admin"
    And user enters password "admin123"
    And clicks on Login button
    Then user should navigate to the application dashboard

  @login
  Scenario: TC_002 Validate a warning message is displayed when user logs in using invalid credentials
    Given user is on application login page
    When user enters username "Admin"
    And user enters password "admin1234"
    And clicks on Login button
    Then a warning is displayed
    """
    Invalid credentials
    """

  @login
  Scenario Outline: TC_003 Validate a warning message is displayed when user logs in using invalid credentials
    Given user is on application login page
    When user enters username "<username>"
    And user enters password "<password>"
    And clicks on Login button
    Then a warning is displayed
    """
    Invalid credentials
    """
    Examples:
      | username | password |
      | Admin | admin |
      | Admin123 | admin |
      | Admin | Pass$1234 |

    @login
    Scenario: TC_005 Validate the social media links in the footer section of the application
      Given user is on application login page
      When user should navigate to the appropriate social media accounts