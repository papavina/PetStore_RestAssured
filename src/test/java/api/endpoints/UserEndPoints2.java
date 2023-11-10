package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.*;

// created for perform crud operations on User api - > Create , Retrieve , Update, Delete


public class UserEndPoints2 {
	
	//this method is to getting URLs from propertiers file	
	public static ResourceBundle getURL() {
		ResourceBundle routes =  ResourceBundle.getBundle("routes");// this is properties file name and we do not specify a full path this
																	// method will fetch the *.properties file from resources folders
		return routes;
		
	}
	
	public static Response createUser(User payload) 
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(getURL().getString("post_url"));
		
		return response;				
		
	}
	
	public static Response readUser(String userName) 
	{
		Response response = given()
			.pathParam("username", userName)
		.when()
			.get(getURL().getString("get_url"));
		
		return response;				
		
	}
	public static Response updateUser(String userName, User payload) 
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(getURL().getString("put_url"));
		
		return response;				
		
	}
	public static Response deleteUser(String userName) 
	{
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(getURL().getString("delete_url"));
		
		return response;				
		
	}

}
