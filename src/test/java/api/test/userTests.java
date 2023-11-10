package api.test;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTests {
	Faker faker;
	User payload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		payload = new User();
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5,10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs generation
		logger = LogManager.getLogger(this.getClass());
		
		
	}
	
	@Test(priority = 1)
	public void testPostUser(){
		logger.info("** creating a user ***");
		Response response = UserEndPoints.createUser(this.payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("** user is created! ***");
		
	}
	
	@Test(priority = 2)
	public void testGetUserByname(){
		logger.info("** reading a user info ***");
		Response response = UserEndPoints.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("** user info is retrieved ***");
		
	}
	
	@Test(priority = 3)
	public void testUpdateUserByname(){
		//updating some data of the User
		logger.info("** updating a user ***");
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		Response response = UserEndPoints.updateUser(this.payload.getUsername(), this.payload);
		response.then().log().all();
		//this is a chai assertion
		response.then().log().body().statusCode(200);
		//testNG assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		//checking data after update
		Response response_after= UserEndPoints.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response_after.getStatusCode(), 200);
		logger.info("** user info is updated ***");
		
	}
	
	@Test(priority = 4 )
	public void testDeleteUserByName() {
		logger.info("** deleting a user ***");
		Response response = UserEndPoints.deleteUser(this.payload.getUsername());
		response.then().log().all();
		//this is a chai assertion
		response.then().log().body().statusCode(200);
		logger.info("** user has been deleted ***");
		
		
	}

}
