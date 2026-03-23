package com.polycoffee.DAO;

import com.polycoffee.Entity.Drink;
import com.polycoffee.Util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO {

    // CREATE
    public int create(Drink entity) throws SQLException {
        String sql = "INSERT INTO DRINKS(Drinks_ID, Name, Price, Image, Description, Active, Category_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return JdbcUtil.executeUpdate(sql,
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getImage(),
                entity.getDescription(),
                entity.isActive(),
                entity.getCategoryId());
    }

    // UPDATE
    public int update(Drink entity) throws SQLException {
        String sql = "UPDATE DRINKS SET Name = ?, Price = ?, Image = ?, Description = ?, Active = ?, Category_ID = ? WHERE Drinks_ID = ?";
        return JdbcUtil.executeUpdate(sql,
                entity.getName(),
                entity.getPrice(),
                entity.getImage(),
                entity.getDescription(),
                entity.isActive(),
                entity.getCategoryId(),
                entity.getId());
    }

    // DELETE
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM DRINKS WHERE Drinks_ID = ?";
        return JdbcUtil.executeUpdate(sql, id);
    }

    // FIND ALL
    public List<Drink> findAll() {
        String sql = "SELECT * FROM DRINKS";
        return findBySql(sql);
    }

    // FIND BY ID
    public Drink findById(Integer id) {
        String sql = "SELECT * FROM DRINKS WHERE Drinks_ID = ?";
        List<Drink> list = findBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    // CORE METHOD
    public List<Drink> findBySql(String sql, Object... args) {
        List<Drink> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtil.executeQuery(sql, args);
            while (rs.next()) {
                Drink d = new Drink(
                        rs.getInt("Drinks_ID"),
                        rs.getInt("Category_ID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Image"),
                        rs.getInt("Price"),
                        rs.getBoolean("Active")
                );
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}