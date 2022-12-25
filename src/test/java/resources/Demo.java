package resources;

public class Demo {
	
	String AddPlaceApi = "/maps/api/place/add/json";
	String GetPlaceApi = "/maps/api/place/get/json";
	String UpdatePlaceApi = "/maps/api/place/update/json";
	String DeletePlaceApi = "/maps/api/place/delete/json";
	
	public String getAddPlaceApiResource()
	{
		return AddPlaceApi;
	}
	
	public String getGetPlaceApiResource()
	{
		return GetPlaceApi;
	}
	
	public String getUpdatePlaceApiResource()
	{
		return UpdatePlaceApi;
	}
			
	public String getDeletePlaceApiResource()
	{
		return DeletePlaceApi;
	}
}
