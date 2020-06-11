package com.example.vaadinViews.view;

import com.example.vaadinViews.dto.GraphicDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import java.util.ArrayList;
import java.util.List;

@Route("graphics")
@Theme(value = Material.class)

public class GraphicsView extends VerticalLayout {
    public GraphicsView(){

        MenuBar menuBar = crearMenu();
        add(menuBar);

        Grid<GraphicDto> grid = gridAddGraophics();
        add(grid);

    }


    private MenuBar crearMenu() {
        MenuBar menuBar = new MenuBar();

        menuBar.setOpenOnHover(true);

        Text selected = new Text("");
        Div message = new Div(new Text("Selected: "), selected);

        MenuItem graficas = menuBar.addItem("Graficas");
        MenuItem dashboards = menuBar.addItem("Dashboards");
        MenuItem informes = menuBar.addItem("Informes");
        menuBar.addItem("Sign Out", e -> selected.setText("Sign Out"));

        graficas.getSubMenu().addItem("Crear",
                e -> selected.setText("Crear"));
        graficas.getSubMenu().addItem("Mostrar",
                e -> selected.setText("Mostrar"));

        dashboards.getSubMenu().addItem("Crear",
                e -> selected.setText("Crear"));
        dashboards.getSubMenu().addItem("Mostrar",
                e -> selected.setText("Mostrar"));

        informes.getSubMenu().addItem("Generar",
                e -> selected.setText("Generar"));
        informes.getSubMenu().addItem("Mostrar",
                e -> selected.setText("Mostrar"));

        return menuBar;
    }

    private Grid<GraphicDto> gridAddGraophics() {

        Grid<GraphicDto> grid = new Grid<>();
        List<GraphicDto> graphicDtoList = new ArrayList<>();
        grid.addColumn(GraphicDto::getIdGraphic).setHeader("idGraphic");
        grid.addColumn(GraphicDto::getName).setHeader("name");
        grid.addColumn(GraphicDto::getQuery).setHeader("query");
        grid.addColumn(GraphicDto::getDescription).setHeader("description");
        grid.addItemClickListener(e -> {
            H2 message = new H2();
            message.add(e.getItem().toString());
            add(message);
        });

        for(int i =0; i<10 ; i++)
        {
            int idGraphic = i;
            String name = "Name";
            String query = "Query";
            String type = "Tipo";
            String description = "description";
            GraphicDto temp = new GraphicDto(idGraphic,name,query,type,description);
            graphicDtoList.add(temp);
        }

        grid.setItems(graphicDtoList);
        return grid;
    }



}
