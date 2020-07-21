Feature: LoginFeature
	This feature deals with the login functionality of the AbleTrace application
	
Scenario: Login with correct username and password, once logged-in click on logout
	Given I Navigate to the login page
	When I Enter username,password and click on Sign-In		
	Then I navigate to home page and Click logout and ok button
	