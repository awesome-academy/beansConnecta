package vn.sun.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInfo {
	
	private final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	private int id;
	
	@NotNull
	@Size(min = 6)
	private String password;
	
	@NotNull
	@Pattern(regexp = EMAIL_PATTERN)
	private String email;
	
	public UserInfo() {}

	public UserInfo(int id, String password,
			@Pattern(regexp = EMAIL_PATTERN) String email) {
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
