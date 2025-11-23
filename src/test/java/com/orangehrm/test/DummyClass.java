package com.orangehrm.test;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;
public class DummyClass extends BaseClass {
	
	@Test
	
	public void dummyTest() {
		
		
		 String title = getDriver().getTitle();
		 ExtentManager.startTest("DummyTest1 Test ");
		   assert title.equals("OrangeHRM"):"Test Failed- Title is not matching";
		   System.out.println("Testpassed-title is Matching ");
		   throw new SkipException("skipping the test as aapart of testing");
		   
			   
		   
		
	}

}
