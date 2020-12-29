package com.restuarantclient.restaurantclient.repository;

import com.restuarantclient.restaurantclient.dao.DishDao;
import com.restuarantclient.restaurantclient.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DishDaoImpl implements DishDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DishDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishList = new ArrayList<>();
        String sql = "SELECT * FROM dishes";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> dishList.add(new Dish(
                        Long.parseLong(String.valueOf(element.get("dish_id"))),
                        String.valueOf(element.get("name")),
                        new BigDecimal(String.valueOf(element.get("price"))))));
        return dishList;
    }

    @Override
    public List<Dish> findByName(String name) {
        return findAll().stream().filter(dish -> dish.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public void saveDish(Dish dish) {
        String sql = "INSERT INTO dishes VALUES (?,?,?)";
        jdbcTemplate.update(sql, dish.getDishId(), dish.getName(), dish.getPrice());
    }

    @Override
    public void removeDishById(long id) {
        String sql = "DELETE FROM dishes WHERE dishes.dish_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
