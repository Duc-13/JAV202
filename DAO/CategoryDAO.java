package com.polycoffee.DAO;

import com.polycoffee.Entity.Category;
import com.polycoffee.Util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    // CREATE
    public int create(Category entity) throws SQLException {
        String sql = "INSERT INTO CATEGORY(Category_ID, Name, Active) VALUES (?, ?, ?)";
        return JdbcUtil.executeUpdate(sql,
                entity.getId(),
                entity.getName(),
                entity.isActive());
    }

    // UPDATE
    public int update(Category entity) throws SQLException {
        String sql = "UPDATE CATEGORY SET Name = ?, Active = ? WHERE Category_ID = ?";
        return JdbcUtil.executeUpdate(sql,
                entity.getName(),
                entity.isActive(),
                entity.getId());
    }

    // DELETE
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM CATEGORY WHERE Category_ID = ?";
        return JdbcUtil.executeUpdate(sql, id);
    }

    // FIND ALL
    public List<Category> findAll() {
        String sql = "SELECT * FROM CATEGORY";
        return findBySql(sql);
    }

    // FIND BY ID
    public Category findById(Integer id) {
        String sql = "SELECT * FROM CATEGORY WHERE Category_ID = ?";
        List<Category> list = findBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    // CORE METHOD
    public List<Category> findBySql(String sql, Object... args) {
        List<Category> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtil.executeQuery(sql, args);
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("Category_ID"),
                        rs.getString("Name"),
                        rs.getBoolean("Active")
                );
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}