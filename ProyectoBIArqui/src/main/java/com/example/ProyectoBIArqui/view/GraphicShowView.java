package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicDashboardController;
import com.example.ProyectoBIArqui.bl.ChartGenerator;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.example.ProyectoBIArqui.dto.GraphicDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("graphic/show")
@Theme(value = Material.class)
public class GraphicShowView extends VerticalLayout implements HasUrlParameter<String> {

    private String id_graphic;
    private GraphicController graphicController;
    private ChartGenerator chartGenerator;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String dashboard) {
        for (String s:beforeEvent.getLocation().getSegments()
        ) {
            System.out.println("AUXILIO ------------>"+s);
        }
        this.id_graphic = beforeEvent.getLocation().getSegments().get(2);
        generarGraphic();
    }

    @Autowired
    public GraphicShowView(GraphicController graphicController, ChartGenerator chartGenerator){
        this.graphicController = graphicController;
        this.chartGenerator = chartGenerator;
    }

    public void generarGraphic(){
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

        MenuItem graficas = menuBar.addItem("Graficas");
        MenuItem dashboards = menuBar.addItem("Dashboards");
        MenuItem informes = menuBar.addItem("Informes");
        menuBar.addItem("Sign Out");

        graficas.getSubMenu().addItem(new RouterLink("Crear", GraphicNewView.class));
        graficas.getSubMenu().addItem(new RouterLink("Mostrar", GraphicsView.class));

        dashboards.getSubMenu().addItem(new RouterLink("Crear", DashboardNewView.class));
        dashboards.getSubMenu().addItem(new RouterLink("Mostrar", DashboardsView.class));

        informes.getSubMenu().addItem(new RouterLink("Crear", ReportNewView.class));
        informes.getSubMenu().addItem(new RouterLink("Mostrar", ReportsView.class));

        return menuBar;
    }


    private Chart TypeGraphic() {
        String type = "ChartType.AREASPLINE";

        Graphic graphic = graphicController.findGraphicByIdGraphic( Integer.parseInt(id_graphic));
        chartGenerator.setGraphic(graphic);
        Chart chart = chartGenerator.GenerarGrafica();
        return chart;
    }

}
