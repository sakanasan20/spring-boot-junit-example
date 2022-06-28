package tw.niq.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

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
	
	@ParameterizedTest
	@ValueSource(strings = {"John", "Kate", "Alice"})
	void valueSourceDemo(String firstName) {
		System.out.println(firstName);
		assertNotNull(firstName);
	}
	
	@DisplayName("Test integer subtraction [minuend, subtrahend, expectResult]")
	@ParameterizedTest
//	@MethodSource()
//	@CsvSource({
//		"33, 1, 32", 
//		"54, 1, 53", 
//		"24, 1, 23"
//	})
	@CsvFileSource(resources = "/integerSubtraction.csv")
	void testIntegerSubtraction(int minuend, int subtrahend, int expectResult) {
		
		System.out.println("Running test " + minuend + "-" + subtrahend + "=" + expectResult);
		
		int actualResult = calculator.integerSubtraction(minuend, subtrahend);
		
		assertEquals(expectResult, actualResult, 
				() -> minuend + "-" + subtrahend + " did not produce " + expectResult);
	}

//	private static Stream<Arguments> testIntegerSubtraction() {
//		
//		return Stream.of(
//				Arguments.of(33, 1, 32), 
//				Arguments.of(54, 1, 53),
//				Arguments.of(24, 1, 23)
//		);
//	}
	
}
