package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.UserDAO;
import vn.sun.entities.User;

@Repository
public class UserDAOImpl extends AbstractBaseDAO<Serializable, User> implements UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadEntities() {
		logger.info("load users");
		return getSession().createQuery("from User").getResultList();
	}

	@Override
	public User findUserByEmail(String email) {
		User user = (User) getSession()
				.createQuery("from User u where u.email = :email")
				.setParameter("email", email)
				.uniqueResult();
		return user;
	}

}
