package com.orangehrm.utilities;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	
	private static ExtentReports extent; 
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static Map<	Long,WebDriver> driverMap=new HashMap<>();
	
	
	
	//intialiize Extent reports 
	public static ExtentReports getReporter()
	
	{
	if(extent==null)
	{
		String reportPath= System.getProperty("user.dir")+"/src/test/resources/ExtentReport/ExtentReport.html";
	    ExtentSparkReporter spark=new ExtentSparkReporter(reportPath);
	    spark.config().setReportName("Automation Test report");
	    spark.config().setDocumentTitle("orangeHRMreport");
	    spark.config().setTheme(Theme.DARK);
	    
	    
	   extent = new ExtentReports();
	    //adding system information 
	   extent.setSystemInfo("operating System", System.getProperty("os.name"));
	   
	   
	    
    	
	}
	return extent;
	
	// how to start the test 

	
	
	
	}
	public static ExtentTest startTest(String testName)
	{
		ExtentTest extenttest=getReporter().createTest(null);
		test.set(extenttest);
		return extenttest;
		
		
	}
	
	//end  a tets 
	public static void endTest()
	{
		getReporter().flush();
		
	}
	public static ExtentTest getTest()
	{
	return test.get() ;
	
	}
	
	//method to get the name of the currentTest
	public static String  getTestName()
	{
		ExtentTest currentTest= getTest();
		if(currentTest!=null)
		{
			return currentTest.getModel().getName();
		}
		else {
			return "No test is currently active for thread";
		}
	}
	//Log a step 
	public static void logStep(String logMessage)
	{
		getTest().info(logMessage);
		
	}
	public static void logStepWithScreenshot(WebDriver driver,String logMessage,String screenshotMessage)
	{
		getTest().pass(logMessage);
		
	}
	//Log a failure
	public static void logFailure(WebDriver driver,String logMessage,String screenshotMessage)
	{
		getTest().fail(logMessage);
		
	}
	
	
	//take a scrrenshot with date and time in file 
	public static void takeScreenshot(WebDriver driver,String screenshotName)
	
	{
	TakesScreenshot ts= (TakesScreenshot)driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	//format date and time for file name 
	String TimeStamp=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	
	//saving the screenshot to a afile
	String destPath = System.getProperty("user.dir")
	        + "/src/test/resources/ExtentReport/screenshots/"
	        + screenshotName + "_"
	        + "Timestamp" + ".png";
   File finalPath=new File(destPath);
   FileUtils:copyFile(src,finalPath);
   

	
	
	}
	
	
	//register web Driver for currenet thread
	public static void registerDriver(WebDriver driver) {
		driverMap.put(Thread.currentThread().getId(),driver);
	
	}

}
