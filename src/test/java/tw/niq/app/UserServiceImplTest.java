package tw.niq.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

	UsersDatabase usersDatabase;
	UserService userService;
	String createdUserId = "";
	
    @BeforeAll
    void setup() {
        // Create & initialize database
    	usersDatabase = new UsersDatabaseMapImpl();
    	usersDatabase.init();
    	userService = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
    	usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
    	
    	// Arrange
    	Map<String, String> user = new HashMap<>();
    	user.put("firstName", "John");
    	user.put("lastName", "Doe");
    	
    	// Act
    	createdUserId = userService.createUser(user);
    	
    	// Assert
    	assertNotNull(createdUserId);
    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {

    	// Arrange
    	Map<String, String> newUserDetails = new HashMap<>();
    	newUserDetails.put("firstName", "Josh");
    	newUserDetails.put("lastName", "Dung");
    	
    	// Act
    	Map updatedUserDetails = userService.updateUser(createdUserId, newUserDetails);
    	
    	// Assert
    	assertEquals(newUserDetails.get("firstName"), updatedUserDetails.get("firstName"), 
    			() -> "Returned value of user's first name is incorrect");
    	assertEquals(newUserDetails.get("lastName"), updatedUserDetails.get("lastName"), 
    			() -> "Returned value of user's last name is incorrect");
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {
    	
    	// Arrange
    	
    	// Act
    	Map userDetails = userService.getUserDetails(createdUserId);
    	
    	// Assert
    	assertNotNull(userDetails, 
    			() -> "User details should not be null");
    	assertEquals(createdUserId, userDetails.get("userId"), 
    			() -> "Returned user details contains incorrect user id");
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

    	// Arrange
    	
    	// Act
    	userService.deleteUser(createdUserId);
    	
    	// Assert
    	assertNull(userService.getUserDetails(createdUserId), 
    			() -> "User should not be found");
    }

}
