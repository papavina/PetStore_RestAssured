package api.test;

import org.testng.Assert;
import org.testng.annotations.*;

import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import api.utilities.DataProviders;
import api.payload.User;

public class DDTests {

	@Test(priority = 1 , dataProvider = "data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID,String UName, String FName, String LName, String uemail, String pwd, String phone) {
		User payload = new User();
		payload.setId(Integer.parseInt(userID));
		payload.setUsername(UName);
		payload.setFirstName(FName);
		payload.setLastName(LName);
		payload.setEmail(uemail);
		payload.setPassword(pwd);
		payload.setPhone(phone);
		//sending post request
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test (priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String uName) {
		Response response = UserEndPoints.deleteUser(uName);
		response.then().log().all();
		//this is a chai assertion
		response.then().log().body().statusCode(200);
		
		
		
	}
	
	
}
