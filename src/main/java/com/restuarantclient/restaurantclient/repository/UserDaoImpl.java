package com.restuarantclient.restaurantclient.repository;

import com.restuarantclient.restaurantclient.dao.UserDao;
import com.restuarantclient.restaurantclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> userList.add(new User(
                        Long.parseLong(String.valueOf(element.get("user_id"))),
                        String.valueOf(element.get("name")),
                        String.valueOf(element.get("surname")),
                        String.valueOf(element.get("phone")),
                        String.valueOf(element.get("email")))));
        return userList;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getEmail(), user.getName(), user.getPhone(),
                user.getSurname());
    }


    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE users.user_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
