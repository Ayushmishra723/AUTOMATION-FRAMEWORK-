package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExtentManager;

import junit.framework.Assert;

public class HomePageTest extends BaseClass {
	private  LoginPage loginpage;
	private  HomePage homepage;
	
	@BeforeMethod
	
	  public void setuppages() { loginpage=new LoginPage(); homepage=
	  new HomePage(getDriver());
	  
	  
	  
	  }
	 
	@Test
	
  public void verifyOrangeHRMLogo()
  
  {  
		//ExtentManager.startTest("Valid login Test ");
	ExtentManager.logStep("naviagting to login page entering username and password");
	  loginpage.Login("Admin","admin123");
		ExtentManager.logStep("verifing logo is visisble or not ");
		

	  	Assert.assertTrue("Logo is not visible",homepage.verifyOrangeHRMlogo());
		ExtentManager.logStep("validation successful");
		ExtentManager.logStep("Logged out successfully");
		
	  	
  }
	
}
