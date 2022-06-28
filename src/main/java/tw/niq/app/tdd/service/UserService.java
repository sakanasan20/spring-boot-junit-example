package tw.niq.app.tdd.service;

import tw.niq.app.tdd.model.User;

public interface UserService {

	User createUser(String firstName, String lastName, String email, String password, String repeatPassword);

}
