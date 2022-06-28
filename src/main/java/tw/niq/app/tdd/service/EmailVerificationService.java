package tw.niq.app.tdd.service;

import tw.niq.app.tdd.model.User;

public interface EmailVerificationService {

	void scheduleEmailConfirmation(User user);
	
}
