/*
 * package com.orangehrm.utilities; import java.io.File; import
 * java.io.IOException; import java.text.SimpleDateFormat; import
 * java.util.Base64; import java.util.Date; import java.util.HashMap; import
 * java.util.Map;
 * 
 * import org.apache.commons.io.FileUtils; import
 * org.openqa.selenium.OutputType; import org.openqa.selenium.TakesScreenshot;
 * import org.openqa.selenium.WebDriver;
 * 
 * import com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.reporter.ExtentSparkReporter; import
 * com.aventstack.extentreports.reporter.configuration.Theme;
 * 
 * public class ExtentManager {
 * 
 * 
 * private static ExtentReports extent; private static ThreadLocal<ExtentTest>
 * test = new ThreadLocal<>(); private static Map< Long,WebDriver> driverMap=new
 * HashMap<>();
 * 
 * 
 * 
 * //intialiize Extent reports public synchronized static ExtentReports
 * getReporter()
 * 
 * { if(extent==null) { String reportPath= System.getProperty("user.dir")+
 * "/src/test/resources/ExtentReport/ExtentReport.html"; ExtentSparkReporter
 * spark=new ExtentSparkReporter(reportPath);
 * spark.config().setReportName("Automation Test report");
 * spark.config().setDocumentTitle("orangeHRMreport");
 * spark.config().setTheme(Theme.DARK);
 * 
 * 
 * extent = new ExtentReports(); //adding system information
 * extent.setSystemInfo("operating System", System.getProperty("os.name"));
 * 
 * 
 * 
 * 
 * } return extent;
 * 
 * // how to start the test
 * 
 * 
 * 
 * 
 * } public synchronized static ExtentTest startTest(String testName) {
 * ExtentTest extenttest=getReporter().createTest(null); test.set(extenttest);
 * return extenttest;
 * 
 * 
 * }
 * 
 * //end a tets public synchronized static void endTest() {
 * getReporter().flush();
 * 
 * } public static ExtentTest getTest() { return test.get() ;
 * 
 * }
 * 
 * //method to get the name of the currentTest public static String
 * getTestName() { ExtentTest currentTest= getTest(); if(currentTest!=null) {
 * return currentTest.getModel().getName(); } else { return
 * "No test is currently active for thread"; } } //Log a step public
 * synchronized static void logStep(String logMessage) {
 * getTest().info(logMessage);
 * 
 * } public static void logStepWithScreenshot(WebDriver driver,String
 * logMessage,String screenshotMessage) { getTest().pass(logMessage);
 * attachScreenshot(driver, screenshotMessage);
 * 
 * } //Log a failure public static void logFailure(WebDriver driver,String
 * logMessage,String screenshotMessage) { getTest().fail(logMessage);
 * 
 * }
 * 
 * 
 * //take a scrrenshot with date and time in file public static String
 * takeScreenshot(WebDriver driver,String screenshotName)
 * 
 * { TakesScreenshot ts= (TakesScreenshot)driver; File src =
 * ts.getScreenshotAs(OutputType.FILE); //format date and time for file name
 * String TimeStamp=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new
 * Date());
 * 
 * //saving the screenshot to a afile String destPath =
 * System.getProperty("user.dir") +
 * "/src/test/resources/ExtentReport/screenshots/" + screenshotName + "_" +
 * "Timestamp" + ".png"; File finalPath=new File(destPath); try {
 * FileUtils.copyFile(src,finalPath); } catch(IOException e) {
 * e.printStackTrace(); }
 * 
 * // convert screenshot to Base64 for embedding in the report String
 * base64Format=" convertToBase64(src)"; return base64Format; } //convert
 * scrrenshot to Base64Format public static String convertToBase64(File
 * screenShotFile)
 * 
 * { String base64Format= " "; //read teh file content into byte
 * 
 * try { byte[] fileContent = FileUtils.readFileToByteArray(screenShotFile);
 * base64Format=Base64.getEncoder().encodeToString(fileContent); } catch
 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
 * //convert the byte array to Base64 String return base64Format;
 * 
 * 
 * } //Attach scrrenshot to report using base64 public synchronized static void
 * attachScreenshot(WebDriver driver,String message) { try { String
 * screenShotBase64 = takeScreenshot(driver, getTestName());
 * getTest().info(message, com.aventstack.extentreports.MediaEntityBuilder
 * .createScreenCaptureFromBase64String(screenShotBase64).build()); } catch
 * (Exception e) { getTest().fail("failed to attach screenshot:"+message); }
 * 
 * 
 * }
 * 
 * 
 * //register web Driver for currenet thread public static void
 * registerDriver(WebDriver driver) {
 * driverMap.put(Thread.currentThread().getId(),driver);
 * 
 * }
 * 
 * }
 */

package com.orangehrm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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
    private static Map<Long, WebDriver> driverMap = new HashMap<>();

    // initialize Extent reports
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/src/test/resources/ExtentReport/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Test report");
            spark.config().setDocumentTitle("orangeHRMreport");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // adding system information
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }

    // start the test
    public synchronized static ExtentTest startTest(String testName) {
        ExtentTest extenttest = getReporter().createTest(testName);
        test.set(extenttest);
        return extenttest;
    }

    // end a test (flush)
    public synchronized static void endTest() {
        getReporter().flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    // method to get the name of the currentTest
    public static String getTestName() {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            return currentTest.getModel().getName();
        } else {
            return "No test is currently active for thread";
        }
    }

    // Log a step
    public synchronized static void logStep(String logMessage) {
        getTest().info(logMessage);
    }

    public static void logStepWithScreenshot(WebDriver driver, String logMessage, String screenshotMessage) {
        getTest().pass(logMessage);
        attachScreenshot(driver, screenshotMessage);
    }

    // Log a failure
    public static void logFailure(WebDriver driver, String logMessage, String screenshotMessage) {
        getTest().fail(logMessage);
        attachScreenshot(driver, screenshotMessage);
    }

    // take a screenshot with date and time in file and return Base64 string
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        // format date and time for file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // saving the screenshot to a file
        String destPath = System.getProperty("user.dir")
                + "/src/test/resources/ExtentReport/screenshots/"
                + screenshotName + "_" + timeStamp + ".png";

        File finalPath = new File(destPath);
        try {
            // ensure parent directories exist
            File parent = finalPath.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            FileUtils.copyFile(src, finalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // convert screenshot file to Base64 for embedding in the report
        return convertToBase64(finalPath);
    }

    // convert screenshot file to Base64 format
    public static String convertToBase64(File screenShotFile) {
        String base64Format = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(screenShotFile);
            base64Format = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Format;
    }

    // Attach screenshot to report using base64
    public synchronized static void attachScreenshot(WebDriver driver, String message) {
        try {
            String screenShotBase64 = takeScreenshot(driver, getTestName());
            getTest().info(message, com.aventstack.extentreports.MediaEntityBuilder
                    .createScreenCaptureFromBase64String(screenShotBase64).build());
        } catch (Exception e) {
            // if attach fails, log the failure
            try {
                getTest().fail("failed to attach screenshot: " + message);
            } catch (Exception ignored) {
            }
        }
    }

    // register web Driver for current thread
    public static void registerDriver(WebDriver driver) {
        driverMap.put(Thread.currentThread().getId(), driver);
    }

    // optional: get driver for current thread
    public static WebDriver getDriverForCurrentThread() {
        return driverMap.get(Thread.currentThread().getId());
    }
}




