package com.cityhospital.patient;

import java.sql.*;
import java.text.DateFormat;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Result;

public class PatientData {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		Connection con = null;
		PreparedStatement ps = null;
		String url = "jdbc:mysql://localhost:3306/hospital";
		String user= "root";
		String password = "tiger";
		String insertquery = "insert into patientdata values(?,?,?,?,?,?,?)";
		
		String retrivequery = "select * from patientdata where name=? and phone=?";
		
		String deletequery = "delete from patientdata where pid=? and name=?";
		
		
	
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			while(true) {
			System.out.println("---------------Welcome to City Hospital--------------------------------");
			System.out.println("Enter the Choice");
			System.out.println("1.Add Patient Data \n2.Check Patient Data \n3. Delete Data  \n4.Exit");
			int ch = sc.nextInt();
			switch(ch) {
			case 1: System.out.println("Enter the pid in a Order");
					int pid=sc.nextInt();
					System.out.println("Enter name of Patient");
					String name= sc.next();
					System.out.println("Enter Gender of Patient");
					String gender =sc.next();
					System.out.println("Enter Blood Group of Patient");
					String bloodgroup = sc.next();
					System.out.println("Enter Phone Number of Patient");
					long phone = sc.nextLong();
					System.out.println("Enter the location of Patient");
					String location = sc.next();
					System.out.println("Enter a date of birth(dd/mm/yyyy)");
					String dateStr = sc.next();
					ps = con.prepareStatement(insertquery);
					ps.setInt(1, pid);
					ps.setString(2, name);
					ps.setString(3, gender);
					ps.setString(4, bloodgroup);
					ps.setLong(5, phone);
					ps.setString(6, location);
					ps.setString(7, dateStr);
					
					if(ps.executeUpdate()>0) {
						System.out.println("Patient data added Succesfully");
					}
					else {
						System.out.println("Invalid data");
					}
					
					break;
					
			case 2: System.out.println("Enter pid to check Patient Data");
					System.out.println("Enter Name of Patient");
					String name1 = sc.next();
					System.out.println("Enter Phone Number of Patient");
					long phone1 = sc.nextLong();
					ps = con.prepareStatement(retrivequery);
					ps.setString(1, name1);
					ps.setLong(2, phone1);
					
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()) {
						System.out.println("----------------Patient Details----------------------");
						System.out.println("PID: "+rs.getInt(1));
						 System.out.println("NAME: "+rs.getString(2));
						 System.out.println("Gender: "+rs.getString(3));
						 System.out.println("Blood Group: "+rs.getString(4));
						 System.out.println("Phone: " +rs.getLong(5));
						 System.out.println("LOCATION: " +rs.getString(6));  
						 System.out.println("Date of Birth: " + rs.getString(7)); 
						 System.out.println("--------------------------------------");
					}
					
					break;
					
					
					
			case 3: System.out.println("Enter the details to delete Patient Data");
			        System.out.println("Enter PID of Patient");
			        int pid2 = sc.nextInt();
			        System.out.println("Enter name of Patient");
			        String name2 = sc.next();
			    	ps = con.prepareStatement(deletequery);
			    	ps.setInt(1, pid2);
			    	ps.setString(2, name2);
			    	if(ps.executeUpdate()>0) {
			    		System.out.println("Deleted Data Succesfully");
			    	}
			    	else {
			    		System.out.println("Data not Found");
			    	}
			    	
			    	break;
			    	
			    	
			        
			        
					
			case 4: System.out.println("Thank You for Visiting City Hospital");
					System.exit(0);
					break;
			
					
			default: System.out.println("Invalid Choice");
						
			}
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
}
