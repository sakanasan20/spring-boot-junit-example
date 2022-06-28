package tw.niq.app.tdd;

public class UserServiceImpl implements UserService {

	@Override
	public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("User's first name is empty");
		}
		
		User user = new User(firstName, lastName, email);
		
		return user;
	}

}
