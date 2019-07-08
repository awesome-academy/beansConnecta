package vn.sun.services.client;

import java.io.Serializable;
import java.util.List;

import vn.sun.entities.User;

public interface UserServices {

	List<User> loadUsers();
	
	boolean saveUserAfterRegister(User user);
	boolean isUserEmailExisted(String email);

	User saveOrUpdate(User user);

	User findById(Serializable key);

	void delete(User user);

	User findByEmail(String email);

}
