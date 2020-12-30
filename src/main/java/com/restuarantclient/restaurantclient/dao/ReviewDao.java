package com.restuarantclient.restaurantclient.dao;

import com.restuarantclient.restaurantclient.model.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> findAll();
    void saveReview(Review review);
    void removeReviewById(long id);
}
