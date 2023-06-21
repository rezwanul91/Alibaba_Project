Feature: Login functionality 

Scenario Outline: Verify login feature functionalities
Given I am in the Alibaba Home page
When I Mouse Hovar in signin menu cart
Then I click in signin button
And I enter the invalid email id from <rowNumber>
And I enter the invalid password
And I click in submit button
And I verify the login error message

Examples:
|rowNumber|
|	0				|
|	1				|
|	2				|
| 3				|