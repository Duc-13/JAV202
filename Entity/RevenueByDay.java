package com.polycoffee.entity;

import java.util.Date;

public class RevenueByDay {
	private Date revenueDate;
	private int totalBills;
	private long totalRevenue;

	public RevenueByDay() {
	}

	public RevenueByDay(Date revenueDate, int totalBills, long totalRevenue) {
		this.revenueDate = revenueDate;
		this.totalBills = totalBills;
		this.totalRevenue = totalRevenue;
	}

	public Date getRevenueDate() {
		return revenueDate;
	}

	public void setRevenueDate(Date revenueDate) {
		this.revenueDate = revenueDate;
	}

	public int getTotalBills() {
		return totalBills;
	}

	public void setTotalBills(int totalBills) {
		this.totalBills = totalBills;
	}

	public long getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(long totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
}