package com.anahuac.calidad.DBUnit;

import static org.junit.Assert.*;
// FIle Imports
import java.io.File;

import org.dbunit.Assertion;
// DB Unit test imports
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
// Test Imports
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anahuac.calidad.DoublesDAO.Alumno;

public class DAOALumnoTest extends DBTestCase{
	
	public DAOALumnoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.cj.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:33060/pruebas_db");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"secret");	
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
	}
	
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = getConnection(); 
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch(Exception e) {
			fail("Error in setup: "+ e.getMessage()); 
		} finally {
			connection.close(); 
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Alumno alumno = new Alumno("004", "alumno004", "hola4@hola.com", 18);
		AlumnoDAOMySQL daoMySql = new AlumnoDAOMySQL(); 
		
		daoMySql.addAlumno(alumno);
		
		// Verify data in database
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("alumnos_tbl");
			
			//Leer el archivo con el resultado esperado
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}	
	}
}
