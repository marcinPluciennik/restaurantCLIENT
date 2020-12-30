package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.ReviewsRestController;
import com.restuarantclient.restaurantclient.model.Review;
import com.restuarantclient.restaurantclient.service.MyService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("7-edit-review")
public class RviewEdit extends VerticalLayout {

    private ReviewsRestController reviewsRestController;
    private MyService service;

    @Autowired
    public RviewEdit(ReviewsRestController reviewsRestController, MyService service) {
        this.reviewsRestController = reviewsRestController;
        this.service = service;

        Label label = new Label();
        label.add("EDIT EXISTING REVIEW:");

        TextField textFieldId = new TextField("ID");
        TextField textFieldRating = new TextField("Rating");
        TextField textFieldText = new TextField("Text");
        Button buttonEdit = new Button("Edit review");

        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        buttonEdit.addClickListener(clickEvent -> {
            if (textFieldRating.getValue().isEmpty() || textFieldText.getValue().isEmpty() ||
                    textFieldId.getValue().isEmpty()){
                dialog.add(new Text("Sorry, at least one of the fields is empty! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            }else{
                try{
                    Review review = new Review(service.convertToLong(textFieldId.getValue()), textFieldText.getValue(),
                            Integer.parseInt(textFieldRating.getValue()));
                    boolean result = reviewsRestController.removeReviewById(review.getReviewId());
                    if (result){
                        reviewsRestController.addReview(review);
                        dialog.add(new Text("Review has been edited! "));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                    }else{
                        dialog.add(new Text("ERROR, there is no id like " + textFieldId.getValue() + "! "));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                    }
                }catch (NumberFormatException e){
                    dialog.add(new Text(" Sorry, id and rating must be a number! "));
                    Button refreshButton = new Button("Close", event -> {
                        UI.getCurrent().getPage().reload();
                        dialog.close();
                    });
                    dialog.add(refreshButton);
                    dialog.open();
                }
            }
        });
        add(label, textFieldId, textFieldRating, textFieldText, buttonEdit);
    }
}
