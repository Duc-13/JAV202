package com.polycoffee.entity;

public class Category {
	private Integer id;
	private String name;
	private boolean active;

	public Category() {
	}

	public Category(Integer id, String name, boolean active) {
		this.id = id;
		this.name = name;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}