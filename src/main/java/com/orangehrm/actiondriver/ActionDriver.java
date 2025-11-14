package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {
  private WebDriver driver ;
  private WebDriverWait wait  ;
  public ActionDriver(WebDriver driver) {
	  this.driver=driver ;
	int explicitWait= Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
	 this.wait= new WebDriverWait(driver,Duration.ofSeconds(explicitWait)); 
  }
	 
	 
	 private void waitForElementToBeClickable(By by) {
	 try
	 {
		 wait.until(ExpectedConditions.elementToBeClickable(by));
		 
	 }catch(Exception e)
	 {
		 System.out.println("element is not clickable: "+e.getMessage());
		 
	 }
	 }
	 //method to click an element 
	 public void click(By by)
	 {
		 try {
			 waitForElementToBeClickable( by);
			 
			 driver.findElement(by).click();
			 
			 driver.findElement(by).click();
			 
		 }catch(Exception e)
		 {
			 System.out.println("unable to click the element ");
			 
		 }
		 
	 }
	 public void waitForPageLoad(int timeOutInSec) {
		    try {
		        wait.withTimeout(Duration.ofSeconds(timeOutInSec))
		            .until(driver -> ((JavascriptExecutor) driver)
		            .executeScript("return document.readyState").equals("complete"));

		        System.out.println("Page loaded successfully");
		    } catch (Exception e) {
		        System.out.println("Page did not load in time: " + e.getMessage());
		    }
		}


	 //scroll to an element 
	 public void scrollToElement(By by )
	 {
		 try {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element =  driver.findElement(by);
		 js.executeScript("arguments[0],scrollIntoView(true)",element);
		 }catch(Exception e)
		 {
			 System.out.println("unable to locate element:"+e.getMessage());
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 //method to compare tow text 
	 public void compareText(By by,String expectedText) {
		 try {
		 waitForElementToBeVisible(by);
		 driver.findElement(by).getText();
		 String actualText= driver.findElement(by).getText();
		 if(expectedText.equals(actualText))
		 {
			 System.out.println("Text are matching:"+actualText+" equals"+expectedText);
			 
		 }
		 else {
			 System.out.println("Text are matching:"+actualText+" notequals"+expectedText);

		 }
		 }catch(Exception e)
		 {
			 System.out.println("unable to comaptre text:"+e.getMessage());
			 
		 }
		 
		 
	 }
	 //method to check if an element is displayed
	 
      public boolean isDisplayed(By by ) {
    	  try {
    	  waitForElementToBeVisible(by);
    	  boolean isDisplayed = driver.findElement(by).isDisplayed();
    	  if(isDisplayed)
    	  {
    		  System.out.println("elemnet is vissble ");
    		  return isDisplayed;
    		  
    	  }
    	  else {
    		  return isDisplayed;
    	  }
    	  }catch(Exception e)
    	  {
    		  System.out.println("element is not displayed");
    		  
    		  return false;
    		  
    	  }
      }
	 
	 
	 
	 
	 
	 //method to enter text in input field 
	 public void enterText(By by ,String value) {
		 waitForElementToBeVisible(by);
		//driver.findElement(by).clear();
		//driver.findElement(by).sendKeys(value);
		 WebElement element = driver.findElement(by);
		 element.clear();
		 element.sendKeys(value);
		 
		
	 }
	 //method to get text form an input field 
	 public String  getText(By by )
	 {
		 waitForElementToBeVisible(by);
		  return driver.findElement(by).getText();
		 
	 }
	 
	 
	 
	 private  void waitForElementToBeVisible(By by) {
		 try {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 
		 
	 }catch(Exception e)
		 {
		 System.out.println("element is not visible:"+e.getMessage());
		 }
	 }
}
