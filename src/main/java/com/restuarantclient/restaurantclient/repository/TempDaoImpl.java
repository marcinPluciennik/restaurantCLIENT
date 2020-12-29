package com.restuarantclient.restaurantclient.repository;

import com.restuarantclient.restaurantclient.dao.TempDao;
import com.restuarantclient.restaurantclient.model.Temp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class TempDaoImpl implements TempDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TempDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Temp findTempToday() {
        String sql = "SELECT * FROM temp_prague WHERE temp_prague.date = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, column) -> new Temp(
                resultSet.getLong("temp_id"),
                resultSet.getString("date"),
                resultSet.getDouble("temp")), LocalDate.now());
    }
}
