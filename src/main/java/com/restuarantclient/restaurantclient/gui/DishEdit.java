package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.DishRestController;
import com.restuarantclient.restaurantclient.model.Dish;
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

@Route("3-edit-dish")
public class DishEdit extends VerticalLayout {

    private DishRestController dishRestController;
    private MyService service;

    @Autowired
    public DishEdit(DishRestController dishRestController, MyService service) {
        this.dishRestController = dishRestController;
        this.service = service;

        Label label = new Label();
        label.add("EDIT EXISTING DISH:");

        TextField textFieldId = new TextField("ID");
        TextField textFieldName = new TextField("name");
        TextField textFieldPrice = new TextField("price");
        Button buttonEdit = new Button("Edit dish");

        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        buttonEdit.addClickListener(clickEvent -> {

            if (textFieldPrice.getValue().isEmpty() || textFieldName.getValue().isEmpty() ||
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
                    Dish dish = new Dish(service.convertToLong(textFieldId.getValue()), textFieldName.getValue(),
                            new BigDecimal(textFieldPrice.getValue()));
                    boolean result = dishRestController.removeDishById(dish.getDishId());
                    if (result){
                        dishRestController.addDish(dish);
                        dialog.add(new Text("Dish has been edited! "));
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
                    dialog.add(new Text(" Sorry, id and price must be a number! "));
                    Button refreshButton = new Button("Close", event -> {
                        UI.getCurrent().getPage().reload();
                        dialog.close();
                    });
                    dialog.add(refreshButton);
                    dialog.open();
                }
            }
        });
        add(label, textFieldId, textFieldName, textFieldPrice, buttonEdit);
    }
}
