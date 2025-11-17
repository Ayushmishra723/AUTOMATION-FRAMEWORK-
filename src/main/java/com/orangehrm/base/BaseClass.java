package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.actiondriver.ActionDriver;

public class BaseClass {
	
    protected   static    Properties prop;
  protected static  WebDriver driver;
  
  
  private static ActionDriver actiondriver;
  @BeforeSuite
  public void loadConfig() throws IOException {
	  
		prop=   new Properties();
		FileInputStream fis =new FileInputStream("src\\main\\resources\\config.properties");//to read the file 
		prop.load(fis);
  }
  @BeforeMethod
  public void setup() throws IOException{
	  System.out.println("setiing up Web Driver for:"+this.getClass().getSimpleName());
	  launchBrowser();
	  configureBrowser(); 
	  staticWait(2);
	  
	  
	  //initialize the actionDriver only once 
	  if(actiondriver==null)
	  {
		  actiondriver = new ActionDriver(driver);
		  System.out.println("ActionDriver instnace is created");
		  
	  }
		  
	  
  }
  
  
  
  private void launchBrowser() {
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver= new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		
	  }
	  else {
		  throw
		  new IllegalArgumentException("Browser not supported:"+browser);
		  
	  }
	  
  }
  private void configureBrowser() {
	  int implicitWait=Integer.parseInt(prop.getProperty("implicitWait"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		//maximize the driver
		driver.manage().window().maximize();
		try {
		driver.get(prop.getProperty("url"));
	  }catch(Exception e)
		{
		  System.out.println("Failed to naviaget the URL:"+e.getMessage());
		  
		}
  }
  
	  
 
  

  
 
	
  @AfterMethod 
 public void tearDown()
 {
	 if(driver!=null) {
		 try {
		 driver.quit();
		 
	 } catch(Exception e)
		 {
		 System.out.println("unable to quit driver:"+e.getMessage());
		 }
	 }
	 System.out.println("webdriver instance is closed");
	 
	 driver=null ;
	 actiondriver= null;
	 
 }


  
  
  
  
  
  
	
//Driver getter method
	/* public WebDriver getDriver() {
		 return driver ;
		 
	 }*/
  
  //getter method for webDriver
  public static  WebDriver getDriver()
  {
	  if(driver==null)
	  {
		  System.out.println("WebDriver is not initialized");
		  throw new IllegalStateException("WebDriver is not initialized");
		  
	  }
	return driver;
	  
  }
  public static  ActionDriver getActionDriver()
  {
	  if(actiondriver==null)
	  {
		  System.out.println("ActionDriver is not initialized");
		  throw new IllegalStateException("ActionDriver is not initialized");
		  
	  }
	return actiondriver;
	  
  }
  //getter method for prop
 public static Properties getProp()
  {
	  return prop;
  }
  
  
  
	public void setDriver(WebDriver driver ) {
		this.driver=driver ;
		
	}

 
  public void staticWait(int seconds) {
	  LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
  }
}

	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   

