package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.DishRestController;
import com.restuarantclient.restaurantclient.model.Dish;
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

@Route("2-add-dish")
public class DishAdd extends VerticalLayout {

    private DishRestController dishRestController;

    @Autowired
    public DishAdd(DishRestController dishRestController) {
        this.dishRestController = dishRestController;

        Label label = new Label();
        label.add("ADD NEW DISH TO MENU:");

        TextField textFieldMName = new TextField("name");
        TextField textFieldPrice = new TextField("price");
        Button buttonAdd = new Button("Add dish");
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        buttonAdd.addClickListener(clickEvent -> {

            if (textFieldPrice.getValue().isEmpty() || textFieldMName.getValue().isEmpty()){
                dialog.add(new Text("Sorry, at least one of the fields is empty! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            }else {
                try {
                    new BigDecimal(String.valueOf(textFieldPrice.getValue()));
                    Dish dish = new Dish(textFieldMName.getValue(), new BigDecimal(textFieldPrice.getValue()));
                    dishRestController.addDish(dish);
                    dialog.add(new Text("Your dish has been added! "));
                    Button refreshButton = new Button("Close", event -> {
                        UI.getCurrent().getPage().reload();
                        dialog.close();
                    });
                    dialog.add(refreshButton);
                    dialog.open();
                } catch (NumberFormatException e) {
                    dialog.add(new Text(" Sorry, price must be a number! "));
                    Button refreshButton = new Button("Close", event -> {
                        UI.getCurrent().getPage().reload();
                        dialog.close();
                    });
                    dialog.add(refreshButton);
                    dialog.open();
                }
            }
        });

        add(label, textFieldMName, textFieldPrice, buttonAdd);
    }
}
