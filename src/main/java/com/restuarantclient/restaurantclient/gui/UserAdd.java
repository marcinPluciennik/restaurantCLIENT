package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.UserRestController;
import com.restuarantclient.restaurantclient.model.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("6-add-user")
public class UserAdd extends VerticalLayout {

    private UserRestController userRestController;

    @Autowired
    public UserAdd(UserRestController userRestController) {
        this.userRestController = userRestController;

        Label label = new Label();
        label.add("ADD NEW USER:");

        TextField textFieldMName = new TextField("name");
        TextField textFieldSurname = new TextField("surname");
        TextField textFieldPhone = new TextField("phone");
        TextField textFieldEmail = new TextField("email");
        Button buttonAdd = new Button("Add user");
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        buttonAdd.addClickListener(clickEvent -> {

            if (textFieldSurname.getValue().isEmpty() || textFieldMName.getValue().isEmpty() ||
            textFieldPhone.getValue().isEmpty() || textFieldEmail.getValue().isEmpty()){
                dialog.add(new Text("Sorry, at least one of the fields is empty! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            }else {
                User user = new User(textFieldMName.getValue(),textFieldSurname.getValue(),
                        textFieldPhone.getValue(), textFieldEmail.getValue());
                userRestController.addUser(user);
                dialog.add(new Text("New user has been added! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            }
        });
        add(label, textFieldMName, textFieldSurname, textFieldPhone, textFieldEmail, buttonAdd);
    }
}
