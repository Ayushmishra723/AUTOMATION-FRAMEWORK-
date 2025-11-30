package com.orangehrm.actiondriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class ActionDriver {
  private WebDriver driver ;
  private WebDriverWait wait  ;
  public static final Logger logger= BaseClass.logger;
  public ActionDriver(WebDriver driver) {
	  this.driver=driver ;
	int explicitWait= Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
	 this.wait= new WebDriverWait(driver,Duration.ofSeconds(explicitWait));
	 System.out.println("webDriver instanve is created ");
	 logger.info("webDriver instance is created ");
	 
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
		 String elementDescription= getElementDescription(by);
		 
		 try {
			 applyBorder(by,"green");
			 waitForElementToBeClickable( by);
			 
			 driver.findElement(by).click();
			 ExtentManager.logStep("clicked an element:"+elementDescription);
			 
			 logger.info("clicked an element:"+elementDescription);
			
			 
			 
			
			 
		 }catch(Exception e)
		 {
			 applyBorder(by,"red");
			 System.out.println("unable to click the element "+e.getMessage());
			 ExtentManager.logFailure(BaseClass.getDriver(), "unable to clcik the element:", elementDescription+"unable to click");
			 logger.error("unable to click the element "+e.getMessage());
			 
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
			 applyBorder(by,"green");
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element =  driver.findElement(by);
		 js.executeScript("arguments[0].scrollIntoView(true)",element);
		 }catch(Exception e)
		 {
			 applyBorder(by,"red");
			 logger.error("unable to locate element:"+e.getMessage());
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 //method to compare tow text ---change the return type 
	 public boolean compareText(By by,String expectedText) {
		 try {
		 waitForElementToBeVisible(by);
		 driver.findElement(by).getText();
		 String actualText= driver.findElement(by).getText();
		 if(expectedText.equals(actualText))
		 {   
			 applyBorder(by,"green");
			 System.out.println("Text are matching:"+actualText+" equals"+expectedText);
			 ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "comapre text ", "text verified successfully");
			 return true ;
		 }
		 else {
			 applyBorder(by,"red");
			 System.out.println("Text are matching:"+actualText+" notequals"+expectedText);
			 ExtentManager.logFailure(BaseClass.getDriver(), "comparison Failed ", "text comapriosn failed");

			 return false;

		 }
		 }catch(Exception e)
		 {
			 logger.error("unable to comaptre text:"+e.getMessage());
			 
		 }
		return false;
		 
		 
	 }
	 //method to check if an element is displayed
	 
      public boolean isDisplayed(By by ) {
    	  try {
    	  waitForElementToBeVisible(by);
    	  applyBorder(by,"green");
    	  boolean isDisplayed = driver.findElement(by).isDisplayed();
    	  logger.info("element is displayed:"+getElementDescription(by));
    	  ExtentManager.logStep("element is displayed:" +getElementDescription(by));
    	  ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "element is displayed:","element is displayed:"+getElementDescription(by));
    	  if(isDisplayed)
    	  {
    		  System.out.println("element is visible ");
    		  return isDisplayed;
    		  
    	  }
    	  else {
    		  return isDisplayed;
    	  }
    	  }catch(Exception e)
    	  {
    		  applyBorder(by,"red");
    		 logger.error("element is not displayed");
    		  
    		  return false;
    		  
    	  }
      }
	 
	 
	 
	 
	 
	 //method to enter text in input field 
	 public void enterText(By by ,String value) {
		 waitForElementToBeVisible(by);
		 applyBorder(by,"green");
		//driver.findElement(by).clear();
		//driver.findElement(by).sendKeys(value);
		 WebElement element = driver.findElement(by);
		 element.clear();
		 element.sendKeys(value);
		 logger.info("entered text on:"+getElementDescription(by)+" "+value);
		 logger.info("value eneterd:"+value  );
		 
		 
		 
		
	 }
	 //method to get text form an input field 
	 public String  getText(By by )
	 { 
		 try {
	 
		 waitForElementToBeVisible(by);
		 applyBorder(by,"green");
		  return driver.findElement(by).getText();
		 
	 }
	 catch(Exception e)
	 {
		 applyBorder(by,"red");
		 logger.error("unable to get the text:"+e.getMessage());
		 return " ";
		 
	 }
	 }
	 
	 
	 private  void waitForElementToBeVisible(By by) {
		 try {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 
		 
	 }catch(Exception e)
		 {
		 logger.error("element is not visible:"+e.getMessage());
		 }
	 }
	 //method to get the description of element using By locator 
	 public String getElementDescription(By locator)
	 {
		 //check for null driver or locator to avoid nullPointerexception
		 if(driver==null)
		 {
			 return "driver is null";
			 
		 }
		 if(locator==null) {
			 return "Locator is null";
		 
	 }
		 try {
	 
		 //find the element using the locator 
		WebElement element = driver.findElement(locator);
		 
		 //getelement description
	String name=	element.getDomAttribute("name");
	String id = element.getAttribute("id");
	String text= element.getText();
	String className= element.getAttribute("class");
	String placeHolder= element.getDomAttribute("placeholder");
	
	// Return the description  based on elements attributes
	if(isNotEmpty(name))
	{
		return "element with name:"+ name;
	}
		
				
	else if (isNotEmpty(id))
	{
		return "Element with id :"+id;
	}
	else if(isNotEmpty(text))
	{
		return "element with text:"+truncate(text,50);
	}
	else if(isNotEmpty(className))
	{
		return "element with class:"+className;
	}
		
	else if(isNotEmpty(placeHolder))
	{
		return "element with placeholder:"+placeHolder;
	}
		 }
		 catch(Exception e)
		 {
	logger.error("unalble to describe the element " +e.getMessage());
		 }
		 return "unable to describe the element" ;
	 }
	 
	 
	 //utility method to check the string is not null or empty 
	 private boolean isNotEmpty(String value )
	 {
		 return value!=null &&!value.isEmpty();
	 }
	 
	 //utility method to truncate long String
	 private String truncate(String value ,int maxLength)
	 {
		 if(value==null || value.length()<=maxLength)
{
     return value ;
     
}
     return value.substring(0,maxLength)+"...";
	 }
	 
	 
	 
	 //utility method to  border the element 
	 public void applyBorder(By by ,String color)
	 {    try {
		 WebElement element=driver.findElement(by);
		 String script = "arguments[0].style.border='3px solid "+color+" '";
		 JavascriptExecutor js= (JavascriptExecutor) driver;
		 js.executeScript(script,element);
		 logger.info("Apllied the border with color  "+color+ " to element "+getElementDescription(by));
	 }
	 catch(Exception e)
	 {
		 logger.warn("Failed to apply the border to an element:"+getElementDescription(by));
		 e.printStackTrace();
	 }
		 
	 }
}

	 


