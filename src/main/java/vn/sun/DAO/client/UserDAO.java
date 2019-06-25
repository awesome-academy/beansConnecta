package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.User;

public interface UserDAO extends BaseDAO<Integer, User> {
	List<User> loadEntities();
	User findUserByEmail(String email);
}
