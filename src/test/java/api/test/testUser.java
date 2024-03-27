package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class testUser {

	Faker faker;
	User userpayload;
	
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority =1)
	public void postMethod()
	{
		logger.info("............Creating user.......");
		Response response=UserEndpoints.createUser(userpayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("...........user created .......");
	}
	
	@Test(priority =2)
	public void getUser()
	{
		logger.info(".......Reading user info.......");
		Response response=UserEndpoints.getUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("........user info is displayed.......");
	}
	
	@Test(priority = 3)
	public void updateuserByuserName()
	{
		logger.info(".........Updating user.......");
		userpayload.setUsername(faker.name().username());
		userpayload.setLastName(faker.name().lastName());
		
		Response updresponse =UserEndpoints.updateUser(this.userpayload.getUsername(), userpayload);
		updresponse.then().log().all();
		Assert.assertEquals(updresponse.getStatusCode(), 200);
		
		Response response=UserEndpoints.getUser(this.userpayload.getUsername());
		response.then().log().all();
		logger.info("........User updated.......");
	}
	
	@Test(priority = 4)
	public void deleteUserByuserName()
	{
		logger.info(".......deleting  user.......");
		Response response=UserEndpoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("........ user deleted.......");
	}
}
