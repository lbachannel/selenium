package com.atteam.selenium.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="Admin")
	private boolean admin;

	@Column(name="Email")
	private String email;

	@Column(name="Fullname")
	private String fullname;

	@Column(name="Password")
	private String password;

	public User() {
	}

	
	public User(String id, String fullname, String password, String email, boolean admin) {
		super();
		this.id = id;
		this.admin = admin;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void showProfile() {
		System.out.printf("|%-10s|%-25s|%-35s|%-10s|\n", id, fullname, email, admin == true ? "Admin" : "User");
	}

}