package com.anahuac.calidad.tareaUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//Import mockito
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.anahuac.calidad.doubles.Dependency;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
//Import hamcrest
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AlertListenerTest {
	
	private cuenta miCuenta; //Object

	@Before
	public void setUp() throws Exception {
		miCuenta = Mockito.mock(cuenta.class);
		// Initialize values
		miCuenta.setZone(1);
		miCuenta.setBalance(300);
		miCuenta.setHolder("Cuenta x");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private void setBehavior() {
		// Set the behavior of the instance
		when(miCuenta.getBalance()).thenReturn(50);
	}
	
	@Test
	public void Verify_test() {
		// Set expected result of the test
		int debito = 50; 
		// Set behavior 
		setBehavior(); 
		// Set running result of the test
		miCuenta.debit(250);
		int runningRes = miCuenta.getBalance(); 
		// Verify that indeed the method is called
		verify(miCuenta).debit(250);
		// Compare the results within a mocked behavior
		assertThat(debito, is(runningRes)); 
	}

}
