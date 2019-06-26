package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.Users;

public interface UsersDAO extends BaseDAO<Integer, Users> {
	List<Users> loadEntities();
	Users findUserByEmail(String email);
}
