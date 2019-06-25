package vn.sun.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "candidates")
public class Candidate {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 4)
	private boolean gender;
	
	@Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;
	
	@Column(name="city")
	private String city;
	
	@Column(name = "address")
	@Type(type = "text")
	private String address;
	
	@Column(name="role")
	private String role;
	
	@Column(name = "objective")
	@Type(type = "text")
	private String objective;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User users;

	public Candidate() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public User getUser() {
		return users;
	}

	public void setUser(User user) {
		this.users = user;
	}
}
