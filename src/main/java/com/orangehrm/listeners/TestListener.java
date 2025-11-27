package com.orangehrm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
    String testName=result.getMethod().getMethodName();
    //start login in extent report 
    ExtentManager.startTest(testName);
    ExtentManager.logStep("Test started :"+testName);
	}
//trigeered when the test success
	@Override
	public void onTestSuccess(ITestResult result) {
		 String testName=result.getMethod().getMethodName();
		 ExtentManager.logStepWithScreenshot(BaseClass.getDriver(),"test passed successfully","Test End:" + testName + "- Testpasssed");
	}
//triggered when a atest fails 
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName= result.getMethod().getMethodName();
		String failureMessage=result.getThrowable().getMessage();
		ExtentManager.logStep(failureMessage);
		ExtentManager.logFailure(BaseClass.getDriver(), "test passed successfully","Test End:" + testName + "- TestFailed");
	}
//trggered whne the test skips 
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	String testName=result.getMethod().getMethodName();
	ExtentManager.logSkip("Test skipped"+testName);
	}
  //trigeered when a  suite starts 
	@Override
	
	public void onStart(ITestContext context) {
		//initialize the etxent report 
	ExtentManager.getReporter();
	}
//triggered when teh suite ends
	
	@Override
	public void onFinish(ITestContext context) {

	ExtentManager.endTest();
	}
	
	

}
