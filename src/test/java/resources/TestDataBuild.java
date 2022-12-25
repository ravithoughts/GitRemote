package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceAPI;
import pojo.LocationDetails;

public class TestDataBuild {
	
	//public AddPlaceAPI addPlacePayload(String name, String address, String language) {
	public AddPlaceAPI addPlacePayload(String name, String address, String language) {
		
		LocationDetails ld = new LocationDetails();
		ld.setLat(-38.383494);
		ld.setLng(33.427362);
		
		List<String> typeslist = new ArrayList<String>();
		typeslist.add("shoe park");
		typeslist.add("shop");
		
		AddPlaceAPI ap = new AddPlaceAPI();
		ap.setLocation(ld);
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setTypes(typeslist);
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setLanguage(language);
		
		return ap;
		
	}
	
	public String deletePlacePayload(String placeid){
		
		return "{\r\n" + 
				"\r\n" + 
				"    \"place_id\":\""+placeid+"\"\r\n" + 
				"}\r\n" + 
				"";
	
	}
	
	public String updatePlacePayload(String placeid, String updatedaddress){
		
		return "{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+updatedaddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}";
	
	}
	
}
