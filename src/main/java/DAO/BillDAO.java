package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entity.Bill;
import Until.JdbcUtil;



public class BillDAO implements CrudDAO<Bill, Integer> {

	public static final int STATUS_WAITING = 0;
	public static final int STATUS_FINISH = 1;
	public static final int STATUS_CANCEL = 2;

	@Override
	public int create(Bill entity) {
		String sql = "INSERT INTO BILLS(Users_ID, Code, Created_at, Total, Status) VALUES (?, ?, ?, ?, ?)";
		try {
			return JdbcUtil.executeUpdate(sql,
					entity.getUserId(),
					entity.getCode(),
					entity.getCreatedAt(),
					entity.getTotal(),
					entity.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Bill entity) {
		String sql = "UPDATE BILLS SET Users_ID=?, Code=?, Created_at=?, Total=?, Status=? WHERE Bills_ID=?";
		try {
			return JdbcUtil.executeUpdate(sql,
					entity.getUserId(),
					entity.getCode(),
					entity.getCreatedAt(),
					entity.getTotal(),
					entity.getStatus(),
					entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM BILLS WHERE Bills_ID=?";
		try {
			return JdbcUtil.executeUpdate(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Bill> findAll() {
		return findBySql("SELECT * FROM BILLS");
	}

	@Override
	public Bill findById(Integer id) {
		List<Bill> list = findBySql("SELECT * FROM BILLS WHERE Bills_ID=?", id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<Bill> findBySql(String sql, Object... args) {
		List<Bill> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql, args);
			while (rs.next()) {
				Bill b = new Bill();
				b.setId(rs.getInt("Bills_ID"));
				b.setUserId(rs.getInt("Users_ID"));
				b.setCode(rs.getString("Code"));
				b.setCreatedAt(rs.getDate("Created_at"));
				b.setTotal(rs.getInt("Total"));
				b.setStatus(rs.getInt("Status"));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}