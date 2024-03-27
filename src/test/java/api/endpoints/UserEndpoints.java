package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
public class UserEndpoints {

	
	public static Response createUser (User payload)
	{
	
		Response respone= given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when()
			.post(Routes.postURL);
		return respone;
	}

	public static Response getUser (String userName)
	{
	
		Response respone= given()
			.pathParam("username",	userName)
		.when()
			.get(Routes.getURL);
		return respone;
	}

	public static Response updateUser (String userName ,User payload)
	{
	
		Response respone= given()
			.contentType("application/json")
			.accept("application/json")
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.updateURL);
		
		return respone;
	}
	public static Response deleteUser (String userName)
	{
	
		Response respone= given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.deleteURL);
		return respone;
	}

}
