package com.polycoffee.dao;

import java.util.ArrayList;
import java.util.List;

import com.polycoffee.entity.Bill;
import com.polycoffee.entity.BillDetail;
import com.polycoffee.entity.Drink;
import com.polycoffee.util.JdbcUtil;

public class BillDetailDAO implements CrudDAO<BillDetail, Integer> {
	BillDAO billDAO = new BillDAO();
	DrinkDAO drinkDAO = new DrinkDAO();

	// Lấy danh sách chi tiết hóa đơn theo billId
	public List<BillDetail> findByBillId(Integer billId) {
		String sql = "SELECT * FROM bill_details WHERE bill_id = ?";
		try {
			return this.findBySql(sql, billId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	// Thêm thức uống vào chi tiết hóa đơn
	public int addDrinkToBill(Integer billId, Integer drinkId) {
		Bill bill = billDAO.findById(billId);
		if (bill == null || !bill.getStatus().equals(BillDAO.STATUS_WAITING)) {
			return 0;
		}

		String sqlCheck = "SELECT * FROM bill_details WHERE bill_id = ? AND drink_id = ?";
		try {
			List<BillDetail> list = this.findBySql(sqlCheck, billId, drinkId);

			if (!list.isEmpty()) {
				BillDetail billDetail = list.get(0);
				return this.updateQuantity(billId, drinkId, billDetail.getQuantity() + 1);
			} else {
				Drink drink = drinkDAO.findById(drinkId);
				String sqlInsert = "INSERT INTO bill_details(bill_id, drink_id, quantity, price) VALUES(?, ?, ?, ?)";

				int rs = JdbcUtil.executeUpdate(sqlInsert, billId, drinkId, 1, drink.getPrice());
				if (rs > 0) {
					billDAO.updateTotal(billId);
				}
				return rs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Cập nhật số lượng
	public int updateQuantity(Integer billId, Integer drinkId, int quantity) {
		Bill bill = billDAO.findById(billId);

		if (bill != null && bill.getStatus().equals(BillDAO.STATUS_WAITING)) {

			if (quantity <= 0) {
				String sql = "DELETE FROM bill_details WHERE bill_id = ? AND drink_id = ?";
				try {
					int rs = JdbcUtil.executeUpdate(sql, billId, drinkId);
					if (rs > 0) billDAO.updateTotal(billId);
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				String sql = "UPDATE bill_details SET quantity = ? WHERE bill_id = ? AND drink_id = ?";
				try {
					int rs = JdbcUtil.executeUpdate(sql, quantity, billId, drinkId);
					if (rs > 0) billDAO.updateTotal(billId);
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// ================= CRUD =================

	@Override
	public int create(BillDetail entity) {
		String sql = "INSERT INTO bill_details(bill_id, drink_id, quantity, price) VALUES (?, ?, ?, ?)";
		try {
			return JdbcUtil.executeUpdate(sql,
					entity.getBillId(),
					entity.getDrinkId(),
					entity.getQuantity(),
					entity.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(BillDetail entity) {
		String sql = "UPDATE bill_details SET bill_id = ?, drink_id = ?, quantity = ?, price = ? WHERE id = ?";
		try {
			return JdbcUtil.executeUpdate(sql,
					entity.getBillId(),
					entity.getDrinkId(),
					entity.getQuantity(),
					entity.getPrice(),
					entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM bill_details WHERE id = ?";
		try {
			return JdbcUtil.executeUpdate(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<BillDetail> findAll() {
		String sql = "SELECT * FROM bill_details";
		return findBySql(sql);
	}

	@Override
	public BillDetail findById(Integer id) {
		String sql = "SELECT * FROM bill_details WHERE id = ?";
		List<BillDetail> list = findBySql(sql, id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<BillDetail> findBySql(String sql, Object... params) {
		List<BillDetail> list = new ArrayList<>();
		try {
			var rs = JdbcUtil.executeQuery(sql, params);
			while (rs.next()) {
				BillDetail bd = new BillDetail(
						rs.getInt("id"),
						rs.getInt("bill_id"),
						rs.getInt("drink_id"),
						rs.getInt("quantity"),
						rs.getInt("price")
				);
				list.add(bd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}