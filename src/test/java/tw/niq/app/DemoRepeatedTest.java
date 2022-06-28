package tw.niq.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

@DisplayName("Repeated Test Demo")
class DemoRepeatedTest {
	
	Calculator calculator;
	
	@BeforeEach
	void beforeEach() {
		calculator = new Calculator();
	}
	
	@DisplayName("Division by zero")
	@RepeatedTest(value = 3, 
		name = "{displayName}. Repetition {currentRepetition} of {totalRepetitions}")
	void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException(
			RepetitionInfo repetitionInfo, TestInfo testInfo) {
		
		System.out.println("Running " + testInfo.getTestMethod().get().getName());
		System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition() 
			+ " of " + repetitionInfo.getTotalRepetitions());
		
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
	
}
