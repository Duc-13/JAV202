package com.polycoffee.Entity;
import java.util.Date;

public class Bill {
	private Integer id;
	private Integer userId;
	private String code;
	private Date createdAt;
	private int total;
	private int status;

	public Bill() {
	}

	public Bill(Integer id, Integer userId, String code,
				Date createdAt, int total, int status) {
		this.id = id;
		this.userId = userId;
		this.code = code;
		this.createdAt = createdAt;
		this.total = total;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}