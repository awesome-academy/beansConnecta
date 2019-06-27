package vn.sun.services.client;

import java.util.List;

import vn.sun.entities.User;

public interface UserServices {

	public List<User> loadUsers();
	
	boolean saveUserAfterRegister(User user);
	boolean isUserEmailExisted(String email);
	
}
