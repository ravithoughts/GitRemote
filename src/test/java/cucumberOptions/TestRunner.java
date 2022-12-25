package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\Dhya\\eclipse-workspace\\RestApiAutomation\\src\\test\\java\\features", 
				 glue = "stepDefinitions",
				 //tags = "@AddPlace and @Smoke",
				 //tags = "@AddPlace or @UpdatePlace or @DeletePlace", // to run only specific group of Tcs
				//tags = "@DeletePlace",
				plugin = "json:target/jsonReports/cucumber-report.json") //to generate cucumber html report

public class TestRunner {
	
	

}
