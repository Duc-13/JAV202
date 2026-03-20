package com.polycoffee.entity;

public class User {
	private Integer id;
	private String email;
	private String password;
	private String fullName;
	private String phone;
	private boolean role;
	private boolean active;

	public User() {
	}

	public User(Integer id, String email, String password, String fullName,
				String phone, boolean role, boolean active) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.phone = phone;
		this.role = role;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}