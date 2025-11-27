package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExtentManager;

import junit.framework.Assert;

public class LoginPageTest extends BaseClass {

	
	private  LoginPage loginpage;
	private  HomePage homepage;
	
	@BeforeMethod
	
	  public void setuppages() { loginpage=new LoginPage(); homepage=
	  new HomePage(getDriver());
	  
	  
	  
	  }
	 
	
	@Test
	public void verifyvalidLoginTest()
	
	{
		
		//ExtentManager.startTest("Valid login Test ");
		ExtentManager.logStep("naviagting to login page enetring username and password");
		loginpage.Login("Admin","admin123");
		ExtentManager.logStep("verifing admin tab is visisble or not ");
		
		Assert.assertTrue("Admin tab should be visisble after successfull login",homepage.isAdminTabVisible());
		ExtentManager.logStep(null);
		homepage.logout();
		ExtentManager.logStep("Logged out successfully");
		staticWait(2);
		
		
	}
	@Test
	
	public void inValidLoginTest() {
		//ExtentManager.startTest("inValid login Test ");
		ExtentManager.logStep("naviagting to login page enetring username and password");
		
		loginpage.Login("admin","xcvv");
		String expectedErrorMessage= "Invalid credentials";
		
		Assert.assertTrue("Text Failed:Invalid ErrorMessage",loginpage.verifyErrorMessage(expectedErrorMessage));
		ExtentManager.logStep("validation successful");
		ExtentManager.logStep("Logged out successfully");
		
	}
}
