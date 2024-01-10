package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints { 

	public static Response createUser(User payload) {
		Response response = given()
				.contentType("application/json")
				.accept("application/json").body(payload)
				.when()
				.post(Routes.post_url);
		
		return response;

	}
	
	public static Response readUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when()
				.get(Routes.get_url);
		
		return response;

	}
	
	public static Response updateUser(String userName, User payload) {
		Response response = given()
				.contentType("application/json")
				.accept("application/json").pathParam("username", userName).body(payload)
				.when()
				.put(Routes.update_url);
		
		return response;

	}
	
	public static Response deleteUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url);
		
		return response;

	}
	
	

}
