package com.anahuac.calidad.DBUnit;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.anahuac.calidad.DoublesDAO.Alumno;
import com.anahuac.calidad.DoublesDAO.AlumnoDAO;

public class AlumnoDAOMySQL {
	
	public Connection getConnectionMySQL() {
		
		Connection con=null;
		try {
			// Establish the driver connector
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			// Set the URI for connecting the MySql database
			con= DriverManager.getConnection(
					"jdbc:mysql://localhost:33060/pruebas_db","root", "secret");
		}catch(Exception e) {System.out.println(e);}
		return con;	
	}
	
	public boolean addAlumno(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;
		try {
		// Declare statement query to run 
		PreparedStatement preparedStatement; 
		preparedStatement = connection.prepareStatement(
							"insert INTO alumnos_tbl(id,nombre,email,edad) values(?,?,?,?)");
		// Set the values to match in the ? on query
		preparedStatement.setString(1, a.getId()); 
		preparedStatement.setString(2, a.getNombre()); 
		preparedStatement.setString(3, a.getEmail());
		preparedStatement.setInt(4, a.getEdad());
					
		// Return the result of connection nad statement
		if(preparedStatement.executeUpdate()>=1) {
			result = true; 
		}
		System.out.println(">> Return: " + result);			
		// Close connection with the database
		connection.close();
		preparedStatement.close(); 
		
		}catch(Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}
	
	public boolean deleteAlumno(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;
		
		try {
		// Declare statement query to run 
		PreparedStatement preparedStatement; 
		preparedStatement = connection.prepareStatement(
							"Delete from alumnos_tbl WHERE id = ?");
		// Set the values to match in the ? on query
		preparedStatement.setString(1, a.getId()); 
					
		// Return the result of connection nad statement
		if(preparedStatement.executeUpdate()>=1) {
			result = true; 
		}
		System.out.println(">> Return: " + result);			
		// Close connection with the database
		connection.close();
		preparedStatement.close(); 
		
		}catch(Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}
	
	public boolean updateEmail(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;
		
		try {
		// Declare statement query to run 
		PreparedStatement preparedStatement; 
		preparedStatement = connection.prepareStatement(
							"UPDATE alumnos_tbl set email = ? WHERE id = ?");
		// Set the values to match in the ? on query
		preparedStatement.setString(1, a.getId()); 
		preparedStatement.setString(3, a.getEmail());
					
		// Return the result of connection and statement
		if(preparedStatement.executeUpdate()>=1) {
			result = true; 
		}
		System.out.println(">> Return: " + result);			
		// Close connection with the database
		connection.close();
		preparedStatement.close(); 
		
		}catch(Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}
	
	public Alumno searchAlumno(String id) {
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement; 
		ResultSet rs; 
		
		Alumno retrieved = null; 
		
		try {
		// Declare statement query to run 
		preparedStatement = connection.prepareStatement(
							"SELECT * from alumnos_tbl WHERE id = ?");
		// Set the values to match in the ? on query
		preparedStatement.setString(1, id); 
		rs = preparedStatement.executeQuery(); 
		
		// Obtain the pointer to the data in generated table
		rs.next();
		
		String retrivedId = rs.getString(1);
		String retrivedName = rs.getString(2);
		String retrivedEmail = rs.getString(3);
		int retrivedAge = rs.getInt(4);
		
		retrieved = new Alumno(retrivedId,retrivedName,retrivedEmail, retrivedAge);	
				
		// Close connection with the database
		connection.close();
		rs.close();
		preparedStatement.close(); 
		
		}catch(Exception e) {
			System.out.println(e);
		}
		// Return statement
		return retrieved;
	}


}
