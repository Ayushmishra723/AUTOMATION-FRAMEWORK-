package com.orangehrm.utilities;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class  DBConnection {
   private static final String DB_URL="jdbc:mysql://localhost:3306/orangehrm_db";
   public static final String DB_USERNAME="root";
   private static final String DB_PASSWORD="";
    public static Connection getDBConnection() throws SQLException
    {
    	try {
			System.out.println("Starting DB connection") ;
			Connection conn=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			System.out.println("Db connection successful");
			return conn;
		} catch (SQLException e) {
		
			System.out.println("error while establishing the connection");
			e.printStackTrace();
			return null;
		}
    
    	
    	
    	
    	}
    
    //Get the emeployee details form the database and store in a map 
     
    public static Map<String ,String> getEmployeeDetails(String employee_id)
    {
    	
    	 String query="SELECT emp_firstname,emp_lastname FROM hs_hr_employee WHERE employee_id="+employee_id;
    	   Map<String,String> employeeDetails = new HashMap<>();
    	   try 
    		   (Connection con= getDBConnection();
    	   
    			   
    			   
    			   Statement stmt= con.createStatement();
    			 ResultSet rs=  stmt.executeQuery(query))
    		   
    			   {
    	   
    		   System.out.println("Executing query:" +query);
    		   if(rs.next())
    		   {
    			String firstName=   rs.getString("emp_firstname");
    					String lastName=   rs.getString("emp_lastname");
    			
    			
    		   //store in a map
    		   employeeDetails.put("firstName",firstName);
    		   employeeDetails.put("lastName",lastName);
    		   
    		   System.out.println("query executed Successfully");
    		   System.out.println("employee data fetched:"+employeeDetails);
    		   
    		   
    	   }
    		   
    
    	   else {
    		   System.out.println("Employees not found");
    		   
    	   }
    			   
    			   }
    
    			   catch(Exception e)
    			   {
    				   System.out.println("Error while executing query");
    				   e.printStackTrace();
    			   }
    			   return employeeDetails;
    	   			   
    }
}
