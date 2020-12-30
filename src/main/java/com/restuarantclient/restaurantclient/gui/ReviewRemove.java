package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.ReviewsRestController;
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

import java.math.BigDecimal;

@Route("7-remove-review")
public class ReviewRemove extends VerticalLayout {

    private ReviewsRestController reviewsRestController;
    private MyService service;

    @Autowired
    public ReviewRemove(ReviewsRestController reviewsRestController, MyService service) {
        this.reviewsRestController = reviewsRestController;
        this.service = service;

        Label label = new Label();
        label.add("REMOVE REVIEW BY ID:");

        TextField textFieldId= new TextField("ID");
        Button buttonRemove = new Button("Remove review");
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        buttonRemove.addClickListener(clickEvent -> {
            if (textFieldId.getValue().isEmpty()){
                dialog.add(new Text("The field ID cannot be empty! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            }else{

                try{
                    new BigDecimal(String.valueOf(textFieldId.getValue()));
                    boolean result = reviewsRestController.removeReviewById(service.convertToLong(textFieldId.getValue()));
                    if (result){
                        dialog.add(new Text("Review has been removed! "));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                    }else{
                        dialog.add(new Text("ERROR, there is no id " + textFieldId.getValue() + "!"));
                        Button refreshButton = new Button("Close", event -> {
                            UI.getCurrent().getPage().reload();
                            dialog.close();
                        });
                        dialog.add(refreshButton);
                        dialog.open();
                    }
                }catch (NumberFormatException e){
                    dialog.add(new Text(" Sorry, id must be a number! "));
                    Button refreshButton = new Button("Close", event -> {
                        UI.getCurrent().getPage().reload();
                        dialog.close();
                    });
                    dialog.add(refreshButton);
                    dialog.open();
                }
            }
        });
        add(label, textFieldId, buttonRemove);
    }
}
