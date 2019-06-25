package vn.sun.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.sun.bean.UserInfo;
import vn.sun.entities.Users;

public class UserConvertHelper {

	private static String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password); 
	}
	
	public static Users convertUserInfoToUserForRegister(UserInfo userInfo) {
		Users user = new Users();
		user.setEmail(userInfo.getEmail());
		user.setPassword(encodePassword(userInfo.getPassword()));
		
		// Set temporary only, will be fixed in next pulls
		user.setRole(Users.role.CANDIDATE); 
		
		return user;
	}
}
