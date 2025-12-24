package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailID() {
		return "testautomation"+System.currentTimeMillis()+"@gmail.com";
		//return "testautomation"+UUID.randomUUID()+"@gmail.com";
	}
	
	@DataProvider(name="regData")
	public Object[][] getUserRegTestData() {
		
		return new Object[][] {
			
			{"abhi","anand","0987654321","abhi@123","yes"},
			{"robinson","matinez","098765665","robin@123","no"},
			{"amber","automation","0987654999","amber@123","yes"}
		};
	}
	
	
	@Test(dataProvider="regData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
				String actRegSuccMessg = 
						registerPage.registerUser(firstName,lastName,getRandomEmailID(),telephone,password,subscribe);
		Assert.assertEquals(actRegSuccMessg, AppConstants.USER_RESG_SUCCESS_MESSG);	
	}
	
	//searchwithnewlyregUser:
	//new User reg -- un/pwd
	//login -- un/pwd
	//search -- Assert
	

}
