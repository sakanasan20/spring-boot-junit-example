package tw.niq.app.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

	@DisplayName("User object created")
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
		assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
		assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
		assertEquals(email, user.getEmail(), "User's email is incorrect");
		assertNotNull(user.getId(), "User's id is missing");
	}
	
}
