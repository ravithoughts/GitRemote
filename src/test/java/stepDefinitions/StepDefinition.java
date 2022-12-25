package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import resources.Demo;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	
	RequestSpecification reqglobal;
	Response resglobal;
	 String placeid;
		
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException 
	{
		TestDataBuild td = new TestDataBuild();	
	  	reqglobal = given().spec(requestSpecification()).body(td.addPlacePayload(name, address, language));
	}
		

	@When("User calls {string} with {string} http request")
	public void user_calls_add_place_api_with_http_request(String apiCalls,String apiMethods) throws IOException 
	{
	    Demo dm = new Demo();
		if (apiCalls.equalsIgnoreCase("AddPlaceAPI"))
		{
		resglobal = reqglobal.when().post(dm.getAddPlaceApiResource())
				.then().extract().response();
		}
		else if (apiCalls.equalsIgnoreCase("GetPlaceAPI"))
		{
		resglobal = given().spec(requestSpecification()).queryParam("place_id", placeid)
				.when().get(dm.getGetPlaceApiResource())
				.then().extract().response();
		}
		
		else if (apiCalls.equalsIgnoreCase("UpdatePlaceAPI"))
		{
		resglobal = reqglobal.when().put(dm.getUpdatePlaceApiResource())
				.then().extract().response();
		}
		else if (apiCalls.equalsIgnoreCase("DeletePlaceAPI"))
		{
		resglobal = reqglobal.when().delete(dm.getDeletePlaceApiResource())
					.then().extract().response();	
		}
		
	}
	
	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int expectedstatuscode) 
	{
		int actualstatuscode = resglobal.getStatusCode();
		System.out.println(actualstatuscode);
		assertEquals(expectedstatuscode, actualstatuscode);
	}
	  	
    @And("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) 
	{
		JsonPath js = new JsonPath(resglobal.asString()); 
		String actualvalue =js.getString(keyvalue); 
	    assertEquals(expectedvalue, actualvalue);
	}
    
    @Then("extract place_id created using AddPlaceAPI")
	public void extract_placeid_created_using_add_place_api()
	{
		JsonPath js = new JsonPath(resglobal.asString()); 
		placeid =js.getString("place_id"); 
			
	}
       
    @Then("verify {string} {string} {string}")
    public void verify(String expectedname, String expectedaddress, String expectedlanguage) 
    {
    	JsonPath js = new JsonPath(resglobal.asString()); 
   		String actualname =js.getString("name"); 
   		String actualaddress =js.getString("address");
   		String actuallanguage =js.getString("language");
   		assertEquals(expectedname, actualname);
   		assertEquals(expectedaddress, actualaddress);
   		assertEquals(expectedlanguage, actuallanguage);
           
       }
    
    @Given("Update Place Payload with {string}")
    public void update_place_payload_with(String updatedaddress) throws IOException {
    	TestDataBuild td = new TestDataBuild();	
	  	reqglobal = given().spec(requestSpecification()).queryParam("place_id", placeid)
	  			.body(td.updatePlacePayload(placeid, updatedaddress));
    }
    
    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
    	TestDataBuild td = new TestDataBuild();	
	  	reqglobal = given().spec(requestSpecification())
	  			.body(td.deletePlacePayload(placeid));
        
    }

}
