package com.polycoffee.entity;

public class BestSellingDrink {
	private int drinkId;
	private String drinkName;
	private int totalQuantitySold;
	private long totalRevenue;

	public BestSellingDrink() {
	}

	public BestSellingDrink(int drinkId, String drinkName,
							int totalQuantitySold, long totalRevenue) {
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.totalQuantitySold = totalQuantitySold;
		this.totalRevenue = totalRevenue;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public int getTotalQuantitySold() {
		return totalQuantitySold;
	}

	public void setTotalQuantitySold(int totalQuantitySold) {
		this.totalQuantitySold = totalQuantitySold;
	}

	public long getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(long totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
}