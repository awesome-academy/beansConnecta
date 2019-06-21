package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.UsersDAO;
import vn.sun.entities.Users;

@Repository
public class UsersDAOImpl extends AbstractBaseDAO<Serializable, Users> implements UsersDAO {

	private static final Logger logger = Logger.getLogger(UsersDAOImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> loadEntities() {
		logger.info("load users");
		return getSession().createQuery("from Users").getResultList();
	}

}
