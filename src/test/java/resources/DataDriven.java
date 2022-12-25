package resources;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;


public class DataDriven {
	
	public static void main(String[] args) throws IOException
	{
		ExcelDriven excel = new ExcelDriven();
		ArrayList<String> data = excel.getData("RestAddbook", "RestAssured");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		System.out.println(data.get(4));
		
	HashMap<String, Object> map = new HashMap<>();
	map.put("name", "apilearn");
	map.put("isbn", "54676");
	map.put("aisle", "treuy");
	map.put("author", "joseph");
	
	HashMap<String, Object> map2 = new HashMap<>();
	map2.put("lat", "12");
	map2.put("lng", "34");
	
	map.put("location", map2);
	
	System.out.println(map);
	
	RestAssured.baseURI = "http://216.10.245.166";
	String response = given().log().all().header("Content-Type", "application/Json")
	.body(map)
	.when().put("Library/Addbook.php").
	then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	String bookid = js.getString("ID");
	System.out.println(bookid);	
	
	
	}
		
}
