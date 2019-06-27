package vn.sun.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.sun.bean.UserInfo;
import vn.sun.entities.User;

public class UserConvertHelper {

	private static String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password); 
	}
	
	public static User convertUserInfoToUserForRegister(UserInfo userInfo) {
		User user = new User();
		user.setEmail(userInfo.getEmail());
		user.setPassword(encodePassword(userInfo.getPassword()));
		
		// Set temporary only, will be fixed in next pulls
		user.setRole(User.role.CANDIDATE); 
		
		return user;
	}
}
