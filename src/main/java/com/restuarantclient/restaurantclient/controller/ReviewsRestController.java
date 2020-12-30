package com.restuarantclient.restaurantclient.controller;

import com.restuarantclient.restaurantclient.dao.ReviewDao;
import com.restuarantclient.restaurantclient.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewsRestController {

    private ReviewDao reviewDao;

    @Autowired
    public ReviewsRestController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReviews")
    public List<Review> getReviews(){
        return reviewDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addReview")
    public ResponseEntity addReview(@RequestBody Review review){
        try{
            reviewDao.saveReview(review);
            return new ResponseEntity(review, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeReview/{id}")
    public boolean removeReviewById(@PathVariable long id){
        Optional<Review> reviewById = reviewDao.findAll().stream()
                .filter(review -> review.getReviewId() == id)
                .findFirst();
        if (reviewById.isPresent()){
            reviewDao.removeReviewById(id);
            return true;
        }
        return false;
    }
}
