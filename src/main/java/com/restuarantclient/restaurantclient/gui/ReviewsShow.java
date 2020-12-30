package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.ReviewsRestController;
import com.restuarantclient.restaurantclient.model.Review;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("reviews-show")
public class ReviewsShow extends VerticalLayout {

    private ReviewsRestController reviewsRestController;
    Grid<Review> grid = new Grid<>(Review.class);

    @Autowired
    public ReviewsShow(ReviewsRestController reviewsRestController) {
        this.reviewsRestController = reviewsRestController;

        Label label = new Label();
        label.add("OUR REVIEWS:");

        grid.setItems(reviewsRestController.getReviews());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("reviewId","rating","reviewText");

        add(label, grid);
    }
}
