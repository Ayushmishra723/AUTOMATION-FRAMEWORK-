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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.LoggerManager;

public class BaseClass {
	      protected static    Properties prop;
  //protected static  WebDriver driver;
  
  
  
  //private static ActionDriver actiondriver;
	      private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	      private static ThreadLocal<ActionDriver> actiondriver=new ThreadLocal<>();
	      
	      
  public static final Logger logger= LoggerManager.getLogger(BaseClass.class);
  @BeforeSuite
  public void loadConfig() throws IOException {
	  
		prop=   new Properties();
		FileInputStream fis =new FileInputStream("src\\main\\resources\\config.properties");//to read the file 
		prop.load(fis);
		logger.info("config.properties file loaded");
		//start the extent report 
	//	ExtentManager.getReporter();--this has been impelmneted in test listener
		
		
		
		
		logger.trace("Trace message  ");
		logger.error("this is error messaage");
		logger.debug("this is debug messaage");
		logger.fatal("this is fatal messaage");
		logger.warn("this is a warn  messaage");
		
  }
  @BeforeMethod
  public synchronized void setup() throws IOException{
	   ExtentManager.startTest(this.getClass().getSimpleName());
	  System.out.println("setiing up Web Driver for:"+this.getClass().getSimpleName());
	  launchBrowser();
	  configureBrowser(); 
	  staticWait(2);
	   logger.info("webDriver initialized and Browser Maximized"
			   );
	   
	  
	  
	  //initialize the actionDriver only once 
	/*  if(actiondriver==null)
	  {
		  actiondriver = new ActionDriver(driver);
		  System.out.println("ActionDriver instnace is created");
		  logger.info("Action driver instance is created "+Thread.currentThread().getId());
		  
	  }
		  

	   
  
  */
		  //initilaized Action driver for the current thread
	   actiondriver.set(new ActionDriver(getDriver()));
	   logger.info("ActionDriver initilaized for thread:"+Thread.currentThread().getId());
	   
	   
  }
  
  
  private synchronized void launchBrowser() {
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("Chrome"))
		{
			//driver= new ChromeDriver();
			driver.set(new ChromeDriver());
			ExtentManager.registerDriver(getDriver());
			logger.info("ChromeDriver instance is created ");//new chnages as per thread
			
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver();
			driver.set(new FirefoxDriver());
			logger.info("Firefox  instance is created ");
			

		
	  }
	
			else if (browser.equals("edge") || browser.equals("msedge")) {
		        // === Option A: explicit path to msedgedriver.exe (recommended if SeleniumManager can't download) ===
		        // Put your msedgedriver.exe, e.g. C:\tools\msedgedriver.exe
				  System.setProperty(
					        "webdriver.edge.driver", 
					        "C:\\Users\\ayush mishra\\Downloads\\edgedriver_win64\\msedgedriver.exe"
					    );
			//driver = new FirefoxDriver();
			driver.set(new EdgeDriver());
			logger.info("edge   instance is created ");
			

		
	  }
	  else {
		  throw
		  new IllegalArgumentException("Browser not supported:"+browser);
		  
	  }
	  
  }
  private void configureBrowser() {
	  int implicitWait=Integer.parseInt(prop.getProperty("implicitWait"));
		
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		//maximize the driver
		driver.get().manage().window().maximize();
		try {
		getDriver().get(prop.getProperty("url"));
	  }catch(Exception e)
		{
		  System.out.println("Failed to naviaget the URL:"+e.getMessage());
		  
		}
  }
  
	  
 
  

  
 
	
  @AfterMethod 
 public synchronized void tearDown()
 {
	 if(driver.get()!=null) {
		 try {
		 driver.get().quit();
		 
	 } catch(Exception e)
		 {
		 System.out.println("unable to quit driver:"+e.getMessage());
		 }
	 }
	 logger.info("webdriver instance is closed");
	 driver.remove();
	 actiondriver.remove();
	 
	// ExtentManager.endTest();
	// driver=null ;
	 //actiondriver= null;
	 
 }


  
  
  
  
  
  
	
//Driver getter method
	/* public WebDriver getDriver() {
		 return driver ;
		 
	 }*/
  
  //getter method for webDriver
  public static  WebDriver getDriver()
  {
	  if(driver.get()==null)
	  {
		  System.out.println("WebDriver is not initialized");
		  throw new IllegalStateException("WebDriver is not initialized");
		  
	  }
	return driver.get();
	  
  }
  public static  ActionDriver getActionDriver()
  {
	  if(actiondriver.get()==null)
	  {
		  System.out.println("ActionDriver is not initialized");
		  throw new IllegalStateException("ActionDriver is not initialized");
		  
	  }
	return actiondriver.get();
	  
  }
  //getter method for prop
 public static Properties getProp()
  {
	  return prop;
  }
  
  
  
	public void setDriver(ThreadLocal<WebDriver> driver ) {
		this.driver=driver ;
		
	}

 
  public void staticWait(int seconds) {
	  LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
  }
}

	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   

