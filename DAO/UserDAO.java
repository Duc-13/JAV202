package com.polycoffee.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.polycoffee.Util.*;
import com.polycoffee.Entity.User;


public class UserDAO implements CrudDAO<User, Integer> {

	@Override
	public int create(User entity) {
		String sql = "INSERT INTO USERS(Email, Password, Full_name, Phone, Role, Active) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			return com.polycoffee.Util.JdbcUtil.executeUpdate(sql,
					entity.getEmail(),
					entity.getPassword(),
					entity.getFullName(),
					entity.getPhone(),
					entity.isRole() ? 1 : 0,
					entity.isActive() ? 1 : 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(User entity) {
		String sql = "UPDATE USERS SET Email=?, Password=?, Full_name=?, Phone=?, Role=?, Active=? WHERE Users_ID=?";
		try {
			return com.polycoffee.Util.JdbcUtil.executeUpdate(sql,
					entity.getEmail(),
					entity.getPassword(),
					entity.getFullName(),
					entity.getPhone(),
					entity.isRole() ? 1 : 0,
					entity.isActive() ? 1 : 0,
					entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM USERS WHERE Users_ID=?";
		try {
			return JdbcUtil.executeUpdate(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> findAll() {
		return findBySql("SELECT * FROM USERS");
	}

	@Override
	public User findById(Integer id) {
		List<User> list = findBySql("SELECT * FROM USERS WHERE Users_ID=?", id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<User> findBySql(String sql, Object... args) {
		List<User> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql, args);
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("Users_ID"));
				u.setEmail(rs.getString("Email"));
				u.setPassword(rs.getString("Password"));
				u.setFullName(rs.getString("Full_name"));
				u.setPhone(rs.getString("Phone"));
				u.setRole(rs.getInt("Role") == 1);
				u.setActive(rs.getInt("Active") == 1);
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// LOGIN CHUẨN
	public User findByEmail(String email) {
		String sql = "SELECT * FROM USERS WHERE Email=? AND Active=1";
		List<User> list = findBySql(sql, email);
		return list.isEmpty() ? null : list.get(0);
	}
}