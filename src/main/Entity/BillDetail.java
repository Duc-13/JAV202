package com.polycoffee.entity;

public class BillDetail {
	private Integer id;
	private Integer billId;
	private Integer drinkId;
	private int quantity;
	private int price;

	public BillDetail() {
	}

	public BillDetail(Integer id, Integer billId, Integer drinkId,
					  int quantity, int price) {
		this.id = id;
		this.billId = billId;
		this.drinkId = drinkId;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Integer getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(Integer drinkId) {
		this.drinkId = drinkId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}