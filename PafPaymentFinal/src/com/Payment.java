package com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author user
 *
 */
public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment?useTimezone=true&serverTimezone=UTC", "root", "");
			if(con != null) {
				System.out.print("Connected to the database...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String paytype, String cno, String expdate, String paycode, String AppointmentId)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement

			String query = "insert into payment (Pay_ID,`Pay_type`,`Pay_cno`,`Pay_expdate`,`Pay_code`,`Appointment_ID`)" + " values (?, ?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, paytype);
			preparedStmt.setString(3, cno);
			preparedStmt.setString(4, expdate);
			preparedStmt.setString(5, paycode);
			preparedStmt.setString(6, AppointmentId);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}

		catch (Exception e)
		{
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
	 
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment Type</th><th>Card Number</th><th>Expiration</th><th>Security Code</th><th>Appointment ID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String pay_ID = Integer.toString(rs.getInt("Pay_ID"));
				String pay_type = rs.getString("Pay_type");
				String pay_cno = Integer.toString(rs.getInt("Pay_cno"));
				String pay_exp_date = rs.getString("Pay_expdate");
				String pay_code = Integer.toString(rs.getInt("Pay_code"));
				String Appointment_ID = rs.getString("Appointment_ID");
	 
				
				 output += "<tr><td><input id='hidAppIDUpdate' name='hidAppIDUpdate' type='hidden' value='" + pay_ID + "'>" + pay_type + "</td>"; 
				 output += "<td>" + pay_cno + "</td>";
				 output += "<td>" + pay_exp_date + "</td>";
				 output += "<td>" + pay_code + "</td>";
				 output += "<td>" + Appointment_ID + "</td>";

	 

	               
	              output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
	                      + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='"+ pay_ID +"'>"+"</td></tr>";
			}
		
			con.close();
			// Complete the html table
			
			output += "</table>";
	}
		catch (Exception e)
		{
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String payID, String paytype, String cno, String expdate, String paycode, String AppointmentId)
	{		
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
	 
			// create a prepared statement
			String query = "UPDATE payment SET Pay_type=?,Pay_cno=?,Pay_expdate=?,Pay_code=?,Appointment_ID=? WHERE Pay_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values
			
			preparedStmt.setString(1, paytype);
			preparedStmt.setInt(2, Integer.parseInt(cno));
			preparedStmt.setString(3, expdate);
			preparedStmt.setInt(4, Integer.parseInt(paycode));
			preparedStmt.setString(5, AppointmentId);
			preparedStmt.setInt(6, Integer.parseInt(payID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
	}
	
		catch (Exception e)
		{
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	 
	}

	public String deletePayment(String pay_ID) {
		
		String output = "";
		
		try
		{
			System.out.println("Delete Method called");
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from payment where Pay_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pay_ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";	
		}
		
		catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	

}
