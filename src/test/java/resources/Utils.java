package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//declare the "req" class variable as "static" to make sure the "req" variable data
	//is available for all the other runs in a particular execution.
	//Else, the req variable becomes null after each run.
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		//implement logging mechanism for request and response
		//using Java class PrintStream and FileOutputStream
		
		if(req==null)
		{
		FileOutputStream fileoutput = new FileOutputStream("logging.txt");
		PrintStream print = new PrintStream (fileoutput);
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(print))
				.addFilter(ResponseLoggingFilter.logResponseTo(print)).build();
		return req;
		}
		
		return req;
		
	}
	
	//method to get any global values from properties file
	//using java class Properties and FileInputStream
	
	public String getGlobalValue(String key) throws IOException
	{
	
		FileInputStream fileinput = new FileInputStream("C:\\Users\\Dhya\\eclipse-workspace\\RestApiAutomation\\src\\test\\java\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(fileinput);
		String globval = prop.getProperty(key);
		return globval;
}
	
}