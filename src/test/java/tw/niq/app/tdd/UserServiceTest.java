package tw.niq.app.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tw.niq.app.tdd.exception.EmailVerificationServiceException;
import tw.niq.app.tdd.exception.UserServiceException;
import tw.niq.app.tdd.model.User;
import tw.niq.app.tdd.repository.UserRepository;
import tw.niq.app.tdd.service.EmailVerificationServiceImpl;
import tw.niq.app.tdd.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl usersService;
	
	@Mock
	EmailVerificationServiceImpl emailVerificationService;

	@Mock
	UserRepository userRepository;

	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;

	@BeforeEach
	void setup() {
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
		when(userRepository.save(any(User.class))).thenReturn(true);

		// Act
		User user = usersService.createUser(firstName, lastName, email, password, repeatPassword);

		// Assert
		assertNotNull(user, "The createUser should not have return null");
		assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
		assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
		assertEquals(email, user.getEmail(), "User's email is incorrect");
		assertNotNull(user.getId(), "User's id is missing");
		verify(userRepository).save(any(User.class));
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

	@DisplayName("If save() method causes RuntimeException, a UserServiceException is thrown")
	@Test
	void testCreateUser_whenSaveMethodThrowsExexception_thenThrowsUserServiceException() {
		
		// Arrange
		when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);

		// Act & Assert
		Assertions.assertThrows(UserServiceException.class, () -> {
			// Act
			usersService.createUser(firstName, lastName, email, password, repeatPassword);
		}, "Should have thrown UserServiceException instead");

		// Assert
	}
	
	@DisplayName("EmailNotification is handled")
	@Test
	void testCreateUser_whenEmailNotificationExceptionThrow_thenThrowsUserServiceException() {
		
		// Arrange
		when(userRepository.save(any(User.class))).thenReturn(true);
		
		doThrow(EmailVerificationServiceException.class)
			.when(emailVerificationService)
			.scheduleEmailConfirmation(any(User.class));
		
//		doNothing().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

		// Act & Assert
		Assertions.assertThrows(UserServiceException.class, () -> {
			// Act
			usersService.createUser(firstName, lastName, email, password, repeatPassword);
		}, "Should have thrown UserServiceException instead");

		// Assert
		verify(emailVerificationService, times(1))
			.scheduleEmailConfirmation(any(User.class));
	}
	
	@DisplayName("Schedule Email Confirmation is executed")
	@Test
	void testCreateUser_whenUserCreated_schedulesEmailConfirmation() {
		
		// Arrange
		when(userRepository.save(any(User.class))).thenReturn(true);

		doCallRealMethod().when(emailVerificationService)
			.scheduleEmailConfirmation(any(User.class));

		// Act
		usersService.createUser(firstName, lastName, email, password, repeatPassword);
		
		// Assert
		verify(emailVerificationService, times(1))
			.scheduleEmailConfirmation(any(User.class));
	}

}
