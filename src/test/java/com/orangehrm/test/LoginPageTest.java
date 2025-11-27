package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;
import org.testng.Assert;




public class LoginPageTest extends BaseClass {

	
	private  LoginPage loginpage;
	private  HomePage homepage;
	
	@BeforeMethod
	
	  public void setuppages() { loginpage=new LoginPage(); homepage=
	  new HomePage(getDriver());
	  
	  
	  
	  }
	 
	
	@Test(dataProvider="validLoginData",dataProviderClass=DataProviders.class)
	public void verifyvalidLoginTest(String username,String password)
	
	{
		
		//ExtentManager.startTest("Valid login Test ");
		ExtentManager.logStep("naviagting to login page enetring username and password");
		loginpage.Login(username,password);
		ExtentManager.logStep("verifing admin tab is visisble or not ");
		
		Assert.assertTrue(homepage.isAdminTabVisible());
		ExtentManager.logStep(null);
		homepage.logout();
		ExtentManager.logStep("Logged out successfully");
		staticWait(2);
		
		
	}
	@Test(dataProvider="invalidLoginData",dataProviderClass=DataProviders.class)
	
	public void inValidLoginTest(String username,String password) {
		//ExtentManager.startTest("inValid login Test ");
		ExtentManager.logStep("naviagting to login page entering username and password");
		
		loginpage.Login(username,password);
		String expectedErrorMessage= "Invalid credentials";
		
		Assert.assertTrue(loginpage.verifyErrorMessage(expectedErrorMessage),"Text Failed:Invalid ErrorMessage");
		ExtentManager.logStep("validation successful");
		ExtentManager.logStep("Logged out successfully");
		
	}
}
