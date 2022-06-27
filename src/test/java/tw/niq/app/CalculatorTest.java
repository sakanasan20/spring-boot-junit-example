package tw.niq.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Math operations in Calculator class")
class CalculatorTest {
	
	Calculator calculator;
	
	@BeforeAll
	static void setup() {
		System.out.println("Executing @BeforeAll method.");
	}
	
	@AfterAll
	static void cleanup() {
		System.out.println("Executing @AfterAll method.");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Executing @BeforeEach method.");
		 calculator = new Calculator();
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("Executing @AfterEach method.");
	}
	
	// test<System Under Test>_<Condition or State Change>_Expected Result

	@DisplayName("Test 4/2 = 2")
	@Test
	void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {
		
		System.out.println("Running test 4/2 = 2");
		
		// Arrange (Given)
		int dividend = 4;
		int divisor = 2;
		int expectResult = 2;
		
		// Act (When)
		int actualResult = calculator.integerDivision(dividend, divisor);
		
		// Assert (Then)
		assertEquals(expectResult, actualResult, () -> "4/2 did not produce 2");
	}
	
//	@Disabled("TODO: Still need to work on this.")
	@DisplayName("Division by zero")
	@Test
	void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException() {
		
		System.out.println("Running division by zero");
		
//		fail("Not implemented yet");
		
		// Arrange (Given)
		int dividend = 4;
		int divisor = 0;
		String expectedExceptionMessage = "/ by zero";
		
		// Act & Assert
		ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
			// Act
			calculator.integerDivision(dividend, divisor);
		}, "Division by zero should have thrown an Arithmetic Exception ");
		
		// Assert
		assertEquals(expectedExceptionMessage, actualException.getMessage(), 
				() -> "Unexpected exception message");
	}
	
	@DisplayName("Test 33-1 = 32")
	@Test
	void testIntegerSubtraction() {
		System.out.println("Running test 33-1 = 32");
		int minuend = 33;
		int subtrahend = 1;
		int expectResult = 32;
		int actualResult = calculator.integerSubtraction(minuend, subtrahend);
		assertEquals(expectResult, actualResult, 
				() -> minuend + "-" + subtrahend + " did not produce " + expectResult);
	}

}
