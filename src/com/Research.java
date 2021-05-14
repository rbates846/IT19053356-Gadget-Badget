
package com;

import java.sql.*;

public class Research {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

//------------------------------------------------Insert---------------------------------------------------------

	public String insertResearch(String name, String institute, String duration, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into researchmanagement(`researchId`,`researcheName`,`researchInstitute`,`researchDuration`,`researchDescription`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, institute);
			preparedStmt.setString(4, duration);
			preparedStmt.setString(5, description);
			
			// execute the statement

			preparedStmt.execute();
			con.close();
			
			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";

		} catch (Exception e) {
			
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Research.\"}";
			 System.err.println(e.getMessage()); 
			
		}

		return output;
	}

//----------------------------------------------------Read------------------------------------------------	

	public String readResearch() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Research Name</th><th>Institute</th>" + "<th>Duration</th>"
					+ "<th>Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from researchmanagement";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String researchId = Integer.toString(rs.getInt("researchId"));
				String name = rs.getString("researcheName");
				String institute = rs.getString("researchInstitute");
				String duration = rs.getString("researchDuration");
				String description = rs.getString("researchDescription");
				

				// Add into the html table
				output += "<tr><td><input id='hidResearchIDUpdate' name='hidResearchIDUpdate' type='hidden' value= '" + researchId
						+ "'>" + name + "</td>";
				output += "<td>" + institute + "</td>";
				output += "<td>" + duration + "</td>";
				output += "<td>" + description + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
				        + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-researchid='" 
				        + researchId + "'>"+ "</td></tr>" ;
						
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Research.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateResearch(String researchId, String name, String institute, String duration, String description)

			{
			String output = "";
			try {
			Connection con = connect();
			if (con == null) {
			return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE researchmanagement SET researcheName=?,researchInstitute=?,researchDuration=?,researchDescription=? WHERE researchId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values with database



			preparedStmt.setString(1, name);
			preparedStmt.setString(2, institute);
			preparedStmt.setString(3, duration);
			preparedStmt.setString(4, description);
			preparedStmt.setInt(5, Integer.parseInt(researchId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}"; 



			} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
			}
			return output;
			}

	public String deleteResearch(String researchId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from researchmanagement where researchId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(researchId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the research.\"}";
			System.err.println(e.getMessage()); 
		}

		return output;
	}
}
