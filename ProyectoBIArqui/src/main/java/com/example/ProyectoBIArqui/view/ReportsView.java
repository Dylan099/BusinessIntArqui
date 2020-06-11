package com.example.ProyectoBIArqui.view;


import com.example.ProyectoBIArqui.dto.DashboardDto;
import com.example.ProyectoBIArqui.dto.ReportDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Route("reports")
@Theme(value = Material.class)

public class ReportsView extends VerticalLayout {
    public ReportsView(){

        MenuBar menuBar = crearMenu();
        add(menuBar);

        Grid<DashboardDto> gridDashboards = gridAddDashboards();
        add(gridDashboards);

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

    private Grid<DashboardDto> gridAddDashboards() {

        Grid<DashboardDto> grid = new Grid<>();
        List<DashboardDto> dashboardDtoList = new ArrayList<>();
        grid.addColumn(DashboardDto::getIdDashboard).setHeader("IdDashboard");
        grid.addColumn(DashboardDto::getName).setHeader("name");
        grid.addColumn(DashboardDto::getDescription).setHeader("Description");
        grid.addItemClickListener(e -> {
            Label dashboardTittle = new Label(e.getItem().getName());
            Label dashboardDescription = new Label(e.getItem().getDescription());
            Grid<ReportDto> reportsDashboards = gridAddReportsDashboards();

            add(dashboardTittle);
            add(dashboardDescription);
            add(reportsDashboards);
        });

        for(int i =0; i<10 ; i++)
        {
            int IdDashboard = i;
            String name = "Name";
            String description = "description";
            DashboardDto temp = new DashboardDto(IdDashboard,name,description);
            dashboardDtoList.add(temp);
        }

        grid.setItems(dashboardDtoList);
        return grid;
    }

    private Grid<ReportDto> gridAddReportsDashboards() {

        Grid<ReportDto> grid = new Grid<>();
        List<ReportDto> reportDtoList = new ArrayList<>();
        grid.addColumn(ReportDto::getIdReport).setHeader("idReport");
        grid.addColumn(ReportDto::getDate).setHeader("Date");
        grid.addItemClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Ir al reporte"+e.getItem().toString()));
            add(message);
        });

        for(int i =0; i<10 ; i++)
        {
            int IdReport = i;
            java.util.Date utilDate = new java.util.Date();
            Date date = new Date(utilDate.getTime());
            int IdDashboard = i;
            ReportDto temp = new ReportDto(IdReport,date,IdDashboard);
            reportDtoList.add(temp);
        }
        grid.setItems(reportDtoList);
        return grid;
    }


}
