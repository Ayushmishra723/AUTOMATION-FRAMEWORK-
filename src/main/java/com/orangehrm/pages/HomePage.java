package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {
  private ActionDriver actionDriver;
  private By adminTab = By.xpath("//*[text()='Admin']");

  private By userIdButton= By.className("oxd-userdropdown-tab");
  private By logoutButton= By.xpath("//a[text()='Logout']");
  private By orangeHRMlogo= By.xpath("//div[@class='oxd-brand-banner']//img");
  //initialize the aActionDriver by passing webDriver instance 
  
  /*public HomePage(WebDriver driver)
  {
	  this.actionDriver= new ActionDriver(driver);
	  
  }*/
  
  public HomePage(WebDriver driver)
  {
	   this.actionDriver= BaseClass.getActionDriver();
	   
  }
  //method to verify if admin tab is visible
  public boolean isAdminTabVisible() {
	return   actionDriver.isDisplayed(adminTab);
  }
  
  
  public boolean verifyOrangeHRMlogo()
  {
	  return actionDriver.isDisplayed(orangeHRMlogo);
	  
  }
  public void logout() {
	  actionDriver.click(userIdButton);
	  actionDriver.click(logoutButton);
	  
  }
}
