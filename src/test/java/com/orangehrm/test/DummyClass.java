package com.orangehrm.test;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
public class DummyClass extends BaseClass {
	
	@Test
	
	public void dummyTest() {
		
		
		 String title = getDriver().getTitle();
		   assert title.equals("OrangeHRM"):"Test Failed- Title is not matching";
		   System.out.println("Testpassed-title is Matching ");
		   
			   
		   
		
	}

}
