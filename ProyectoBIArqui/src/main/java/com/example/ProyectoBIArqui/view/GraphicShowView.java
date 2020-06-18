package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.bl.ChartGenerator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Route("graphic/show")
@Theme(value = Material.class)
public class GraphicShowView extends VerticalLayout {
    public GraphicShowView(){
        MenuBar menuBar = crearMenu();
        add(menuBar);

        Label title = new Label("Titulo de la grafica");
        Chart chart = TypeGraphic();
        Label description = new Label("Descripcion de la grafica");

        FormLayout buttonsLayout = new FormLayout();
        buttonsLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("50em", 1),
                new FormLayout.ResponsiveStep("50em", 2));

        Button edit = new Button("Editar");
        edit.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Editar"));
            add(message);
        });

        Button delete = new Button("Borrar");
        delete.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Borrar"));
            add(message);
        });

        buttonsLayout.add(edit, delete);


        setHorizontalComponentAlignment(Alignment.CENTER,title);
        setHorizontalComponentAlignment(Alignment.CENTER,chart);
        setHorizontalComponentAlignment(Alignment.CENTER,description);
        setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);

        add(title);
        add(chart);
        add(description);
        add(buttonsLayout);

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


    // Aca vamos a tener que hacer un super case
    private Chart TypeGraphic() {
        String type = "ChartType.AREASPLINE";
        //ChartGenerator chartGenerator = new ChartGenerator();
        //Chart chart = chartGenerator.TypeGraphic();

        return null;
    }

}
