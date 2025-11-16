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
	public void setuppages()
	{
		loginpage=new LoginPage(getDriver());
		homepage= new HomePage(getDriver());
		
		
		
	}
	
	@Test
	public void verifyvalidLoginTest()
	
	{
		loginpage.Login("Admin","admin123");
		Assert.assertTrue("Admin tab should be visisble after successfull login",homepage.isAdminTabVisible());
		homepage.logout();
		staticWait(2);
		
		
	}
}
