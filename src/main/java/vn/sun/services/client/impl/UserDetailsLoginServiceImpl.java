package vn.sun.services.client.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.sun.DAO.client.UserDAO;
import vn.sun.entities.User;


public class UserDetailsLoginServiceImpl implements UserDetailsService {
	
	private static final Logger logger = Logger.getLogger(UserDetailsLoginServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		try {
			User user = userDAO.findUserByEmail(email);
			return (user == null) ? null : createUserDetails(user);
		} catch (Exception e) {
			logger.error("has error by loadUserByUsername method inside UserDetailsLoginServiceImpl");
			return null;
		}
	}

	private UserDetails createUserDetails(User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, user.getAuthorities());
	}

}
