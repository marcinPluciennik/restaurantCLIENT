package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.UserRestController;
import com.restuarantclient.restaurantclient.model.User;
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


@Route("7-edit-user")
public class UserEdit extends VerticalLayout {

    private UserRestController userRestController;
    private MyService service;

    @Autowired
    public UserEdit(UserRestController userRestController, MyService service) {
        this.userRestController = userRestController;
        this.service = service;

        Label label = new Label();
        label.add("EDIT EXISTING USER:");

        TextField textFieldId = new TextField("ID");
        TextField textFieldName = new TextField("name");
        TextField textFieldSurname = new TextField("surname");
        TextField textFieldPhone = new TextField("phone");
        TextField textFieldEmail = new TextField("email");
        Button buttonEdit = new Button("Edit user");

        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        buttonEdit.addClickListener(clickEvent -> {

            if (textFieldSurname.getValue().isEmpty() || textFieldName.getValue().isEmpty() ||
                    textFieldId.getValue().isEmpty() || textFieldPhone.getValue().isEmpty() ||
                    textFieldEmail.getValue().isEmpty()){
                dialog.add(new Text("Sorry, at least one of the fields is empty! "));
                Button refreshButton = new Button("Close", event -> {
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                dialog.add(refreshButton);
                dialog.open();
            }else{
                try{
                    User user = new User(service.convertToLong(textFieldId.getValue()), textFieldName.getValue(),
                            textFieldSurname.getValue(),textFieldPhone.getValue(), textFieldEmail.getValue());
                    boolean result = userRestController.removeUserById(user.getUserId());
                    if (result){
                        userRestController.addUser(user);
                        dialog.add(new Text("User has been edited! "));
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
        add(label, textFieldId, textFieldName, textFieldSurname, textFieldPhone, textFieldEmail, buttonEdit);
    }
}
