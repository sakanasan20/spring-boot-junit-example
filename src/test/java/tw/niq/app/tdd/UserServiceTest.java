package tw.niq.app.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
	
	UserService usersService;
	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;
	
	@BeforeEach
	void setup() {
		usersService = new UserServiceImpl();		
		firstName = "John";
		lastName = "Doe";
		email = "john.doe@example.com";
		password = "12345678";
		repeatPassword = "12345678";
	}

	@DisplayName("User object created")
	@Test
	void testCreateUser_whenUserDetailsProvided_returnUserObject() {
		
		// Arrange
		
		// Act
		User user = usersService.createUser(firstName, lastName, email, password, repeatPassword);
		
		// Assert
		assertNotNull(user, "The createUser should not have return null");
		assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
		assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
		assertEquals(email, user.getEmail(), "User's email is incorrect");
		assertNotNull(user.getId(), "User's id is missing");
	}
	
	@DisplayName("Empty first name cause correct exception")
	@Test
	void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
		
		// Arrange
		String firstName = "";
		String expectedExceptionMessage = "User's first name is empty";

		// Act & Assert
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			// Act
			usersService.createUser(firstName, lastName, email, password, repeatPassword);
		}, "Empty first name should have caused an Illegal Argument Exception");
		
		// Assert
		assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception error message is not correct");
	}
	
}
