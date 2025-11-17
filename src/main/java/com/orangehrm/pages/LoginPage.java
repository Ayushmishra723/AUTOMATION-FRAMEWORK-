package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {
   private ActionDriver actionDriver ;
   //define locators using by
   private By userNameField = By.name("username");
   private By passwordField = By.cssSelector("input[type='password']");
   private By loginButton= By.xpath("//button[normalize-space()='Login']");
   private By errorMessage= By.xpath("//p[text()=\"Invalid credentials\"]");
   
 /* public LoginPage(WebDriver driver)
  {
	  this.actionDriver= new ActionDriver(driver);
	  
  }*/
   
   public LoginPage()
   {
	   this.actionDriver= BaseClass.getActionDriver();
	   
   }
   
   //method to perform login 
   public void Login(String username,String password)
   
   {
	   actionDriver.enterText(userNameField,username);
	   actionDriver.enterText( passwordField ,password);
	   actionDriver.click(loginButton);
	   
   }
   //method to check if error message is displayed 
   public boolean iserrorMessageDisplayed() {
	    return actionDriver.isDisplayed(errorMessage);
	   
   }
   
   //method to get text from error message
   public String  getErrorMessageText()
   {
	   return actionDriver.getText(errorMessage);
	   
   }
   //verify if error is correct or not 
   public boolean verifyErrorMessage(String expectedError)
   {
	   return actionDriver.compareText(errorMessage, expectedError);
   }
}
