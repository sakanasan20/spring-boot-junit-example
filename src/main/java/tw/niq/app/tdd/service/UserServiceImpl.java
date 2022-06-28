package tw.niq.app.tdd.service;

import tw.niq.app.tdd.exception.UserServiceException;
import tw.niq.app.tdd.model.User;
import tw.niq.app.tdd.repository.UserRepository;

public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	EmailVerificationService emailVerificationService;

	public UserServiceImpl(UserRepository userRepository, 
			EmailVerificationService emailVerificationService) {
		this.userRepository = userRepository;
		this.emailVerificationService = emailVerificationService;
	}

	@Override
	public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("User's first name is empty");
		}
		
		User user = new User(firstName, lastName, email);
		
		boolean isUserCreated;
		
		try {
			isUserCreated = userRepository.save(user);
		} catch (RuntimeException ex) {
			throw new UserServiceException(ex.getMessage());
		}
		
		if (!isUserCreated) throw new UserServiceException("Could not create user");
		
		try {
			emailVerificationService.scheduleEmailConfirmation(user);
		} catch (RuntimeException ex) {
			throw new UserServiceException(ex.getMessage());
		}
		
		
		return user;
	}

}
