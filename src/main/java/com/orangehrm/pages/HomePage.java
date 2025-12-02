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
  private By pimtab= By.xpath("//span[text()=\"PIM\"]");
  private By employeeSearch=By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/div/div/input");
  private By searchButton=By.xpath("//button[@type='submit']");
  private By emplFirstName = By.xpath("//div[@class='oxd-table-card']/div/div[3]");
  private By emplyLastName= By.xpath("//div[@class='oxd-table-card']/div/div[4]");
 
  
  
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
  //method to naviagate to pim tab 
  public void clickOnPIMTab()
  {
	  actionDriver.click(pimtab);
	  }
  
  public void employeeSearch(String value)
  {
	  actionDriver.enterText(employeeSearch, value);
	  actionDriver.click(searchButton);
	  actionDriver.scrollToElement(emplFirstName);
	  
  }
  //verify employee first 
  public boolean verifyEmployeeFullName(String firstNameDb, String lastNameDb)
  {
      boolean firstNameMatch = actionDriver.compareText(emplFirstName, firstNameDb);
      boolean lastNameMatch  = actionDriver.compareText(emplyLastName, lastNameDb);

      return firstNameMatch && lastNameMatch;
  }

  
  
  public void logout() {
	  actionDriver.click(userIdButton);
	  actionDriver.click(logoutButton);
	  
  }
  
  
  
}
