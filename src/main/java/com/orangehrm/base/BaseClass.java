package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.LoggerManager;

public class BaseClass {
	      static    Properties prop;
  protected static  WebDriver driver;
  
  
  
  private static ActionDriver actiondriver;
  public static final Logger logger= LoggerManager.getLogger(BaseClass.class);
  @BeforeSuite
  public void loadConfig() throws IOException {
	  
		prop=   new Properties();
		FileInputStream fis =new FileInputStream("src\\main\\resources\\config.properties");//to read the file 
		prop.load(fis);
		logger.info("config.properties file loaded");
		logger.trace("Trace message  ");
		logger.error("this is error messaage");
		logger.debug("this is debug messaage");
		logger.fatal("this is fatal messaage");
		logger.warn("this is a warm  messaage");
		
  }
  @BeforeMethod
  public void setup() throws IOException{
	  System.out.println("setiing up Web Driver for:"+this.getClass().getSimpleName());
	  launchBrowser();
	  configureBrowser(); 
	  staticWait(2);
	   logger.info("webDriver initialized and Browser Maximized"
			   );
	   
	  
	  
	  //initialize the actionDriver only once 
	  if(actiondriver==null)
	  {
		  actiondriver = new ActionDriver(driver);
		  System.out.println("ActionDriver instnace is created");
		  logger.info("Action driver instance is created ");
		  
	  }
		  
	  
  }
  
  
  
  private void launchBrowser() {
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver= new ChromeDriver();
			logger.info("ChromeDriver instance is craeted ");
			
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			logger.info("Firefox  instance is craeted ");
			

		
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
	 logger.info("webdriver instance is closed");
	 
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

	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   

