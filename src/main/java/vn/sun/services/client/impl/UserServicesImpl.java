package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.sun.DAO.client.UserDAO;
import vn.sun.entities.User;
import vn.sun.services.client.UserServices;

public class UserServicesImpl implements UserServices {
	private static final Logger logger = Logger.getLogger(UserServicesImpl.class);

	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<User> loadUsers() {
		try {
			return userDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadUsers method");
			return null;
		}
	}

	@Override
	public boolean saveUserAfterRegister(User user) {
		try {
			if (isUserEmailExisted(user.getEmail())) return false;
			userDAO.saveOrUpdate(user);
			return true;
		} catch (Exception ex) {
			logger.error("Error in saveUserAfterRegister: " + ex.getMessage());
			throw ex;
		}
	}

	@Override
	public boolean isUserEmailExisted(String email) {
		User userInDB = userDAO.findUserByEmail(email);
		return (userInDB == null) ? false : true;
	}

}
