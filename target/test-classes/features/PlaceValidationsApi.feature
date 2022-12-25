#@Regression
Feature: Validating Place APIs

# Implement parametrization - running same test for # multiple set of data sets
# by using Scenario Outline and Examples

@AddPlace @Smoke
Scenario Outline: Verify if AddPlaceAPI is working
   
    Given Add Place Payload with "<Name>" "<Address>" "<Language>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	Then extract place_id created using AddPlaceAPI
	When User calls "GetPlaceAPI" with "GET" http request
    Then The API call got success with status code 200
	And verify "<Name>" "<Address>" "<Language>"

Examples:
    |Name	    |Address	    |Language	        |
    |summer name|summer address	|summer language	|	
    |rainy name |rainy address  |rainy language	    |
    
    
@UpdatePlace
Scenario Outline: Verify UpdatePlcae API is working
	Given Add Place Payload with "<Name>" "<Address>" "<Language>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	Then extract place_id created using AddPlaceAPI
	Given Update Place Payload with "<AddressUpdate>"
	When User calls "UpdatePlaceAPI" with "PUT" http request
	Then The API call got success with status code 200
	And "msg" in response body is "Address successfully updated"
	When User calls "GetPlaceAPI" with "GET" http request
	Then The API call got success with status code 200
	And verify "<Name>" "<AddressUpdate>" "<Language>"	
Examples:
    |Name	    |Address	        |Language	        |AddressUpdate |
    |AA House   |AA House address	|AA House Language	|AAA Housechange|	
    |BB House   |BB House address   |BB House Language  |BBB Housechange|
    
 @DeletePlace
 Scenario Outline: Verify DeletePlcae API is working
	Given Add Place Payload with "<Name>" "<Address>" "<Language>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	Then extract place_id created using AddPlaceAPI
	Given Delete Place Payload
	When User calls "DeletePlaceAPI" with "DELETE" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	When User calls "GetPlaceAPI" with "GET" http request
	Then The API call got success with status code 404
	And "msg" in response body is "Get operation failed, looks like place_id  doesn't exists"	
	
Examples:
    |Name	    |Address	        |Language	        |Address Update |
    |AA House   |AA House address	|AA House Language	|AAA Housechange|	
    |BB House   |BB House address   |BB House Language  |BBB Housechange|
    
# @DeletePlace
# Scenario: Verify DeletePlcae API is working
##	Given Add Place Payload with "<Name>" "<Address>" "<Language>"
##	When User calls "AddPlaceAPI" with "POST" http request
##	Then The API call got success with status code 200
##	And "status" in response body is "OK"
##	Then extract place_id created using AddPlaceAPI
#	Given Delete Place Payload
#	When User calls "DeletePlaceAPI" with "DELETE" http request
#	Then The API call got success with status code 200
#	And "status" in response body is "OK"
##	When User calls "GetPlaceAPI" with "GET" http request
##	Then The API call got success with status code 404
##	And "msg" in response body is "Get operation failed, looks like place_id  doesn't exists"	
#	
