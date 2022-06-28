package tw.niq.app;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class MethodOrderedByNameTest {
	
	@Test
	void testC() {
		System.out.println("Running test C");
	}
	
	@Test
	void testD() {
		System.out.println("Running test D");
	}
	
	@Test
	void testA() {
		System.out.println("Running test A");
	}
	
	@Test
	void testB() {
		System.out.println("Running test B");
	}
	
}
