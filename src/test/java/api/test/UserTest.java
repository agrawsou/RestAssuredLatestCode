package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User user;
	
	public Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		user = new User();

		user.setId(faker.idNumber().hashCode());
		user.setUserName(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5, 10));
		user.setPhone(faker.phoneNumber().cellPhone());
		
		///logs
		
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("Creating User");
		Response response = UserEndPoints.createUser(user);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("User created");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		Response response = UserEndPoints.readUser(user.getUserName());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testupdateUserByName() {

		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(user.getUserName(), user);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(user.getUserName());

		responseAfterUpdate.then().log().all();

		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}
	
	@Test(priority = 4)
	public void deleteUserByName() {
		Response response = UserEndPoints.deleteUser(user.getUserName());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
