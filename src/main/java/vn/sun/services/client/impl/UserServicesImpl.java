package vn.sun.services.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

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

	@Override
	public User saveOrUpdate(User user) {
		try {
			userDAO.saveOrUpdate(user);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save or update user");
			throw e;
		}
		return user;
	}

	@Override
	public User findById(Serializable key) {
		try {
			User result_user = userDAO.findById(key);
			if (result_user != null) return result_user;
			logger.error("user is null");
			return null;
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant find user");
			return null;
		}
	}
	
	@Override
	public User findByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

	@Override
	public void delete(User user) {
		try {
			userDAO.delete(user);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant delete user");
			throw e;
		}
	}
}
