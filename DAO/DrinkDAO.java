package DAO;

import Entity.Drink;
import util.JdbcUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO implements CrudDAO<Drink, Integer> {

    @Override
    public int create(Drink entity) {
        String sql = "INSERT INTO drinks (category_id, name, price, image, description, active) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return JdbcUtil.executeUpdate(sql, 
                entity.getCategoryId(), 
                entity.getName(), 
                entity.getPrice(), 
                entity.getImage(),
                entity.getDescription(), 
                entity.isActive()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Drink entity) {
        String sql = "UPDATE drinks SET category_id = ?, name = ?, price = ?, image = ?, description = ?, active = ? WHERE id = ?";
        try {
            return JdbcUtil.executeUpdate(sql, 
                entity.getCategoryId(), 
                entity.getName(), 
                entity.getPrice(), 
                entity.getImage(),
                entity.getDescription(), 
                entity.isActive(), 
                entity.getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM drinks WHERE id = ?";
        try {
            return JdbcUtil.executeUpdate(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Drink> findAll() {
        return findBySql("SELECT * FROM drinks");
    }

    @Override
    public Drink findById(Integer id) {
        String sql = "SELECT * FROM drinks WHERE id = ?";
        List<Drink> list = findBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Drink> findBySql(String sql, Object... values) {
        List<Drink> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtil.executeQuery(sql, values);
            while (rs.next()) {
                Drink drink = new Drink();
                drink.setId(rs.getInt("id"));
                drink.setCategoryId(rs.getInt("category_id"));
                drink.setName(rs.getString("name"));
                drink.setPrice(rs.getInt("price"));
                drink.setImage(rs.getString("image"));
                drink.setDescription(rs.getString("description"));
                drink.setActive(rs.getBoolean("active"));
                list.add(drink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}