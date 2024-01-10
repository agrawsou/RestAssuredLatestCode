package api.endpoints;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	public static FileInputStream fi;
	public static Properties prop;
	
	static {
		
		try {
			fi = new FileInputStream("C:\\Users\\10701116\\rest-assured-workspace\\PetStoreAutomation\\src\\test\\resources\\config.properties");
			prop = new Properties();
			prop.load(fi);
		} catch (Exception e) {
			e.printStackTrace();
		}
}

	public static Response createUser(User payload) {
		
		Response response = given()
				.contentType("application/json")
				.accept("application/json").body(payload)
				.when()
				.post(prop.getProperty("post_url"));
		
		return response;

	}
	
	public static Response readUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when()
				.get(prop.getProperty("get_url"));
		
		return response;

	}
	
	public static Response updateUser(String userName, User payload) {
		Response response = given()
				.contentType("application/json")
				.accept("application/json").pathParam("username", userName).body(payload)
				.when()
				.put(prop.getProperty("update_url"));
		
		return response;

	}
	
	public static Response deleteUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when()
				.delete(prop.getProperty("delete_url"));
		
		return response;

	}
	
	

}
