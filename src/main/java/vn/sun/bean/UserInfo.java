package vn.sun.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInfo {
	
	private final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private final String PHONE_NUM_PATTERN = "(\\+84|0)\\d{9,10}";
	private int id;
	
	@NotNull
	@Size(min = 6)
	private String password;
	
	@NotNull
	@Size(min = 6)
	private String passwordConfirm;
	
	@NotNull
	@Pattern(regexp = EMAIL_PATTERN)
	private String email;
	
	private String fullName;
	
	private String gender;
	
	@Pattern(regexp = PHONE_NUM_PATTERN)
	private String phone;
	
	private String address;
	
	private Date dob;
	
	@NotNull
	private String role;
	
	private int companyId;
	
	public UserInfo() {}

	public UserInfo(int id, @NotNull @Size(min = 6) String password, @NotNull @Size(min = 6) String passwordConfirm,
			@NotNull @Pattern(regexp = EMAIL_PATTERN) String email, String fullName, int companyId,
			String gender, @Pattern(regexp = PHONE_NUM_PATTERN) String phone, String address, 
			String role, Date dob) {
		this.id = id;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
		this.fullName = fullName;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.role = role;
		this.companyId = companyId;
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "UserInfo [\nid=" + id + "\nrole=" + role + "\ncompanyId=" + companyId
				+ ", \npassword=" + password + ", \npasswordConfirm=" + passwordConfirm + ", \nemail=" + email + ", \nfullName="
				+ fullName + ", \ngender=" + gender + ", \nphone=" + phone + ", \naddress=" + address + ", \ndob=" + dob + "\n]";
	}
	
}
