package tw.niq.app.tdd;

public interface UserService {

	User createUser(String firstName, String lastName, String email, String password, String repeatPassword);

}
