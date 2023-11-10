package api.endpoints;

/*
 * Swagger URI -> https://petstore.swagger.io/
 * 
 * Create User	POST
 * Get User GET
 * Update User PUT
 * Delete User DELETE					
 * 
 * */

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2/";
	//USER module
	public static String post_url = base_url + "user";
	public static String get_url = base_url + "user/{username}";
	public static String put_url = base_url + "user/{username}";
	public static String delete_url = base_url + "user/{username}";
	
}
