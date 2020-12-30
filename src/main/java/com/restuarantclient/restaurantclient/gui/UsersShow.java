package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.UserRestController;
import com.restuarantclient.restaurantclient.model.User;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("users-show")
public class UsersShow extends VerticalLayout {

    private UserRestController userRestController;
    Grid<User> grid = new Grid<>(User.class);

    @Autowired
    public UsersShow(UserRestController userRestController) {
        this.userRestController = userRestController;

        Label label = new Label();
        label.add("USERS:");

        grid.setItems(userRestController.getUsers());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("userId", "name","surname", "phone", "email");

        add(label, grid);
    }
}
