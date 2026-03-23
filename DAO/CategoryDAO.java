package DAO;

import Entity.Category;
import util.JdbcUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CrudDAO<Category, Integer> {

    @Override
    public int create(Category entity) {
        String sql = "INSERT INTO categories (name, active) VALUES (?, ?)";
        try {
            return JdbcUtil.executeUpdate(sql, entity.getName(), entity.isActive());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Category entity) {
        String sql = "UPDATE categories SET name = ?, active = ? WHERE id = ?";
        try {
            return JdbcUtil.executeUpdate(sql, entity.getName(), entity.isActive(), entity.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        try {
            return JdbcUtil.executeUpdate(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Category> findAll() {
        return findBySql("SELECT * FROM categories");
    }

    @Override
    public Category findById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<Category> list = findBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Category> findBySql(String sql, Object... values) {
        List<Category> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtil.executeQuery(sql, values);
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setActive(rs.getBoolean("active"));
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}