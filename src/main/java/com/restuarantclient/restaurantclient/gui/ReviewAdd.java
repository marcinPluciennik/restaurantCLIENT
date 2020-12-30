package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.ReviewsRestController;
import com.restuarantclient.restaurantclient.model.Review;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("review-add")
public class ReviewAdd extends VerticalLayout {

    private ReviewsRestController reviewsRestController;

    @Autowired
    public ReviewAdd(ReviewsRestController reviewsRestController) {
        this.reviewsRestController = reviewsRestController;

        Label label = new Label();
        label.add("ADD NEW REVIEW:");

        TextField textFieldText = new TextField("Text");
        TextField textFieldRating = new TextField("Rating 1-5");
        Button buttonAdd = new Button("Add review");
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        buttonAdd.addClickListener(clickEvent -> {

            if (textFieldText.getValue().isEmpty() || textFieldRating.getValue().isEmpty()) {
                dialog.add(new Text("Sorry, at least one of the fields is empty! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            } else {
                try {
                    Integer.parseInt(textFieldRating.getValue());
                    if (1 <= Integer.parseInt(textFieldRating.getValue()) &&
                            Integer.parseInt(textFieldRating.getValue()) <= 5) {
                        Review review = new Review(Integer.parseInt(textFieldRating.getValue()), textFieldText.getValue());
                        reviewsRestController.addReview(review);
                        dialog.add(new Text("Your dish has been added! "));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                    }else{
                        dialog.add(new Text(" Sorry, rating must be a number 1-5! "));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                    }
                }catch(NumberFormatException e){
                        dialog.add(new Text(" Sorry, rating must be a number 1-5! "));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                }
            }
        });
        add(label, textFieldRating, textFieldText, buttonAdd);
    }
}
