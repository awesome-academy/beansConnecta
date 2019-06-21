package vn.sun.services.impl.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.sun.DAO.client.UsersDAO;
import vn.sun.entities.Users;
import vn.sun.services.client.UsersServices;

public class UsersServicesImpl implements UsersServices {
	private static final Logger logger = Logger.getLogger(UsersServicesImpl.class);

	@Autowired
	private UsersDAO usersDAO;

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO userDAO) {
		this.usersDAO = userDAO;
	}

	@Override
	public List<Users> loadUsers() {
		try {
			return usersDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadUsers method");
			return null;
		}
	}

}
