# Reset password functionality

Feature: Reset Page functionality

  @login @reset
  Scenario: TC_004 Validate Forgot Password functionality
    Given user is on application login page
    Given user is on application login page
    And user clicks on Forgot your Password link
    Then Reset Password dialog is displayed
    When user enters "valid" username
    And clicks on Reset Password button
    Then Reset Password link sent successfully dialog is displayed with note
      """
      If the email does not arrive, please contact your OrangeHRM Administrator.
      """