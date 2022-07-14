package io.shlink.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(name = "admin_user", nullable = false)
	private Boolean isAdmin;

	public User() {
		super();
	}

	public User(Integer id, String email, String password, Boolean isAdmin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;

		if (isAdmin == null)
			this.isAdmin = false;
		else
			this.isAdmin = isAdmin;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		if (isAdmin == null)
			this.isAdmin = false;
		else
			this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
	
}