package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entity.BillDetail;
import Until.JdbcUtil;


public class BillDetailDAO implements CrudDAO<BillDetail, Integer> {

	@Override
	public int create(BillDetail entity) {
		String sql = "INSERT INTO BILL_DETAILS(Bills_ID, Drinks_ID, Quantity, Price) VALUES (?, ?, ?, ?)";
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
		String sql = "UPDATE BILL_DETAILS SET Quantity=?, Price=? WHERE Bills_ID=? AND Drinks_ID=?";
		try {
			return JdbcUtil.executeUpdate(sql,
					entity.getQuantity(),
					entity.getPrice(),
					entity.getBillId(),
					entity.getDrinkId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// KHÔNG dùng vì DB không có id
		return 0;
	}

	// 👉 Xóa đúng
	public int deleteByBillAndDrink(Integer billId, Integer drinkId) {
		String sql = "DELETE FROM BILL_DETAILS WHERE Bills_ID=? AND Drinks_ID=?";
		try {
			return JdbcUtil.executeUpdate(sql, billId, drinkId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<BillDetail> findAll() {
		return findBySql("SELECT * FROM BILL_DETAILS");
	}

	@Override
	public BillDetail findById(Integer id) {
		return null; // không dùng
	}

	public BillDetail findByBillAndDrink(Integer billId, Integer drinkId) {
		List<BillDetail> list = findBySql(
				"SELECT * FROM BILL_DETAILS WHERE Bills_ID=? AND Drinks_ID=?",
				billId, drinkId);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<BillDetail> findBySql(String sql, Object... args) {
		List<BillDetail> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql, args);
			while (rs.next()) {
				BillDetail bd = new BillDetail();
				bd.setBillId(rs.getInt("Bills_ID"));
				bd.setDrinkId(rs.getInt("Drinks_ID"));
				bd.setQuantity(rs.getInt("Quantity"));
				bd.setPrice(rs.getInt("Price"));
				list.add(bd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BillDetail> findByBillId(Integer billId) {
		return findBySql("SELECT * FROM BILL_DETAILS WHERE Bills_ID=?", billId);
	}
}