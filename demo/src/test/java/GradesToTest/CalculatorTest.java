package GradesToTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 // code executed before all test methods
		/*
		 * Code in this method is executed only once, before all test methods in the test class. 
		 * Typically you put code that prepares test environment here, e.g. 
		 * initializing resources such as opening a database connection. 
		 * The method marked with this annotation must be static.
		 */
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// code executed after all test method
		/*
		 * Code in this method is executed only once, after all test methods in the test class. 
		 * Due to this behavior, you can put code to clean up test environment here, e.g. 
		 * closing the database connection. 
		 * Note that this kind of method must be static.
		 */
	}

	@BeforeEach
	void setUp() throws Exception {
		// code executed before each test method
	}

	@AfterEach
	void tearDown() throws Exception {
		// code executed after each test method
	}

	@Test
	void testAdd() {
		// test method
		Calculator calc = new Calculator();
		int a = 14;
		int b = 53;	
		int expected = 67;
		assertEquals(expected, calc.add(a, b));
	}
	@Test
	void testSubstract() {
		// test method
		Calculator calc = new Calculator();
		int a = 53;
		int b = 14;	
		int expected = 39;
		assertEquals(expected, calc.substract(a, b));
	}

}
