package com.orangehrm.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.ExtentManager;

public class DBVerificationTest extends BaseClass {
	
	private  LoginPage loginpage;
	private  HomePage homepage;
	
	@BeforeMethod
	
	  public void setuppages() { 
		loginpage=new LoginPage(); 
		homepage=
	  new HomePage(getDriver());
	}
	  @Test
	  
	  
	  public void VerifyEmployeeFromDB()
	  {
		  ExtentManager.logStep("Logging with Admin credentials");
		  loginpage.Login(prop.getProperty("username"),prop.getProperty("password"));
		  ExtentManager.logStep("click on pim tab ");
		  homepage.clickOnPIMTab();
		  
		  ExtentManager.logStep("search for Employee");
		  homepage.employeeSearch("AYUSH MISHRA");
		  
		  ExtentManager.logStep("Get the employee name form db ");
		  String employee_id = "1";
		  
		  //fetch the data into  a map
		 Map<String,String> employeeDetails= DBConnection.getEmployeeDetails(employee_id);
		String emplFirstName= employeeDetails.get("firstName");
		// String emplLastName=employeeDetails.get("lastName");
		 
		 String emplFirstAndLastName=(emplFirstName).trim();
		 ExtentManager.logStep("verify the employee first and last name");
		 Assert.assertTrue(homepage.verifyEmployeeFirstName(emplFirstAndLastName),"First and Last name are not matching ");
		ExtentManager.logStep("DB Validatiom completed");
		
		 
		 
		  
		  
		  
		  
		  
	  }
	  
	  
	  
	 
	

}
