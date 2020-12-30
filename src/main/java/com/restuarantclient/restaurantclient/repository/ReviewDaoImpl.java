package com.restuarantclient.restaurantclient.repository;

import com.restuarantclient.restaurantclient.dao.ReviewDao;
import com.restuarantclient.restaurantclient.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviewList = new ArrayList<>();
        String sql = "SELECT * FROM reviews";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> reviewList.add(new Review(
                        Long.parseLong(String.valueOf(element.get("review_id"))),
                        String.valueOf(element.get("review_text")),
                        Integer.parseInt(String.valueOf(element.get("rating"))))));
        return reviewList;
    }

    @Override
    public void saveReview(Review review) {
        String sql = "INSERT INTO reviews VALUES (?,?,?)";
        jdbcTemplate.update(sql, review.getReviewId(), review.getRating(), review.getReviewText());
    }

    @Override
    public void removeReviewById(long id) {
        String sql = "DELETE FROM reviews WHERE reviews.review_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
