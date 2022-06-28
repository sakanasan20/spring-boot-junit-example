package tw.niq.app.tdd;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserServiceTest {

	@Test
	void testCreateUser_whenUserDetailsProvided_returnUserObject() {
		
		// Arrange
		UserService usersService = new UserServiceImpl();
		
		String firstName = "John";
		String lastName = "Doe";
		String email = "john.doe@example.com";
		String password = "12345678";
		String repeatPassword = "12345678";
		
		// Act
		User user = usersService.createUser(firstName, lastName, email, password, repeatPassword);
		
		// Assert
		assertNotNull(user, "The createUser should not have return null");
	}
	
}
