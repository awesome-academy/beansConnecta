package vn.sun.services.client;

import java.util.List;

import vn.sun.entities.User;

public interface UserServices {

	public List<User> loadUsers();
	public void createUser();
	
	boolean saveUserAfterRegister(User user);
	boolean isUserEmailExisted(String email);
	
}
