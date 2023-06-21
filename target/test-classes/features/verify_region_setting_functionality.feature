Feature: Verify Region setting functionality

@Regression
Scenario Outline: Verify Region setting  functionality of Alibaba

Given I am in landing page
When I input data in search box <rowNumber>
And I Click on search button

#When I mouseHover on region setting
#And I select languauge as Hindi
#And I select currency as INR
#Then I click save button

Examples:
|rowNumber|
|0		 		|
|1				|
|2	     	|