package vn.sun.helper;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.sun.DAO.client.impl.UserDAOImpl;
import vn.sun.bean.UserInfo;
import vn.sun.entities.Candidate;
import vn.sun.entities.Company;
import vn.sun.entities.User;

public class UserConvertHelper {

	private static String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password); 
	}
	
	public static User convertUserInfoToUserForRegister(UserInfo userInfo, Company company) {
		User user = new User();
		user.setEmail(userInfo.getEmail());
		user.setPassword(encodePassword(userInfo.getPassword()));
		user.setPhone(userInfo.getPhone());
		user.setCreateTime(new Date());
		
		if(userInfo.getRole().equals("candidate")) {
			user.setRole(User.role.CANDIDATE);
			Candidate candidate = new Candidate();
			candidate.setAddress(userInfo.getAddress());
			candidate.setGender(userInfo.getGender().equals("male") ? true : false);
			candidate.setName(userInfo.getFullName());
			user.setCandidate(candidate);
		}
		else 
		{
			user.setRole(User.role.COMPANY);
			user.setCompany(company);
		}
		
		return user;
	}
}
