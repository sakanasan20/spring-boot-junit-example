package tw.niq.app.tdd.service;

import tw.niq.app.tdd.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {

	@Override
	public void scheduleEmailConfirmation(User user) {
		System.out.println("scheduleEmailConfirmation is called");
	}

}
