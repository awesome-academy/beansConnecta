package vn.sun.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "password")
	@Type(type = "text")
	private String password;

	public enum role {
		COMPANY, CANDIDATE
	}

	@Column(columnDefinition = "enum('COMPANY','CANDIDATE')")
	@Enumerated(EnumType.STRING)
	private role role;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	private Date createTime;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	private Date updateTime;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "companyId")
	private Company company;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public role getRole() {
		return role;
	}

	public void setRole(vn.sun.entities.User.role role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
		candidate.setUser(this);
	}	
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.role.name()));
		return authorities;
	}

}
