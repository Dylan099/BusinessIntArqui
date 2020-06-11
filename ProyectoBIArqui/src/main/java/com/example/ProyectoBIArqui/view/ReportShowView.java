package com.example.vaadinViews.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
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


@Route("report/show")
@Theme(value = Material.class)
public class ReportShowView extends VerticalLayout {
    public ReportShowView(){
        MenuBar menuBar = crearMenu();
        add(menuBar);

        Label title = new Label("Titulo del informe");



        Label description = new Label("Descripcion del informe + fecha");

        FormLayout buttonsLayout = new FormLayout();
        buttonsLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("50em", 1),
                new FormLayout.ResponsiveStep("50em", 2));

        Button donwloadPDF = new Button("Descargar PDF");
        donwloadPDF.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Descargar PDF"));
            add(message);
        });

        Button delete = new Button("Borrar");
        delete.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Borrar"));
            add(message);
        });

        buttonsLayout.add(donwloadPDF, delete);


        setHorizontalComponentAlignment(Alignment.CENTER,title);
     //   setHorizontalComponentAlignment(Alignment.CENTER,viewer);
        setHorizontalComponentAlignment(Alignment.CENTER,description);
        setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);

        add(title);
     //   add(viewer);
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

}
