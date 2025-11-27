package com.orangehrm.utilities;

import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {

    private static final String FILE_PATH = System.getProperty("user.dir")
            + "/src/test/resources/testdata/TestData.xlsx";
    
    @DataProvider(name="validLoginData")
    public static Object[][] validLoginData() {
        try {
            System.out.println("[DP] start validLoginData");
            return getSheetData("validLoginData");
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("DP failed: " + t.getMessage(), t);
        }
    }

    @DataProvider(name="invalidLoginData")
    public static Object[][] invalidLoginData()
    {
    	return getSheetData("invalidLoginData");
    	
    }

    // Utility method to get sheet data
    public static Object[][] getSheetData(String sheetName) {

        List<String[]> sheetData = ExcelReaderUtility.getSheetData(FILE_PATH, sheetName);

        Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];

        for (int i = 0; i < sheetData.size(); i++) {
            data[i] = sheetData.get(i);  // converting each row (String[]) into Object[]
        }

        return data;
    }
}
