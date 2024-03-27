package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.dataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	
	@Test(priority=1, dataProvider="Data", dataProviderClass=dataProviders.class)
	public void createUserByPost(String userID, String userName, String fname, String lname, String email,String pwd, String ph)
	{
		User user=new User();
		user.setId(Integer.parseInt(userID));
		user.setUsername(userName);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		user.setPassword(pwd);
		user.setPhone(ph);
		
		Response response=UserEndpoints.createUser(user);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=dataProviders.class)
	public void deleteUser(String username)
	{
		Response response=UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
