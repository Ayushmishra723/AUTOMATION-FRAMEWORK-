package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

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
	  loginpage.Login("Admin","admin123");
	  	Assert.assertTrue("Logo is not visible",homepage.verifyOrangeHRMlogo());
	  	
  }
	
}
