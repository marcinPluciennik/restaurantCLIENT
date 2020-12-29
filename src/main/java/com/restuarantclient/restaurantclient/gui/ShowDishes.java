package com.restuarantclient.restaurantclient.gui;

import com.restuarantclient.restaurantclient.controller.DishRestController;
import com.restuarantclient.restaurantclient.controller.TempRestController;
import com.restuarantclient.restaurantclient.model.Dish;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("1-restaurant-menu")
public class ShowDishes extends VerticalLayout {

    private DishRestController dishRestController;
    private TempRestController tempRestController;
    Grid<Dish> grid = new Grid<>(Dish.class);
    TextField filter = new TextField();


    @Autowired
    public ShowDishes(DishRestController dishRestController, TempRestController tempRestController) {
        this.dishRestController = dishRestController;
        this.tempRestController = tempRestController;

        Dialog dialogWelcome = new Dialog();
            dialogWelcome.add(new Text("WELCOME TO RESTAURANT Láska nebeská"));
        dialogWelcome.setWidth("380px");
        dialogWelcome.setHeight("100px");
        dialogWelcome.open();

        Label label = new Label();
        label.add("TEMPERATURE IN PRAGUE TODAY: " +
                (int)Math.round(tempRestController.getTempToday().getTemp()) + " [C]");
        label.getElement().getStyle().set("color", "red");

        filter.setPlaceholder("Filter by dish name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());

        Details componentInfo = new Details("Láska nebeská - RESTAURANT IN PRAGUE",
                new Text("MENU:"));

        grid.setItems(dishRestController.getDishes());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("dishId", "name","price");

        add(componentInfo, label, filter, grid);

    }

    private void update() {
        grid.setItems(dishRestController.getDishesByName(filter.getValue()));
    }
}
