package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

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
		loginpage.Login("Admin","admin123");
		Assert.assertTrue("Admin tab should be visisble after successfull login",homepage.isAdminTabVisible());
		homepage.logout();
		staticWait(2);
		
		
	}
	@Test
	
	public void inValidLoginTest() {
		loginpage.Login("admin","xcvv");
		String expectedErrorMessage= "Invalid credentials1";
		Assert.assertTrue("Text Failed:Invalid ErrorMessage",loginpage.verifyErrorMessage(expectedErrorMessage));
		
	}
}
