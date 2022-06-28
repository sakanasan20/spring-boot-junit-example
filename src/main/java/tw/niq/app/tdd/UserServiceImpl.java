package tw.niq.app.tdd;

public class UserServiceImpl implements UserService {

	@Override
	public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

		User user = new User(firstName, lastName, email);
		
		return user;
	}

}
