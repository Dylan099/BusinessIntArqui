package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicDashboardController;
import com.example.ProyectoBIArqui.api.QueryController;
import com.example.ProyectoBIArqui.bl.ChartGenerator;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.example.ProyectoBIArqui.dto.GraphicDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Route("graphic/show")
@Theme(value = Lumo.class,variant = Lumo.DARK)
public class GraphicShowView extends VerticalLayout implements HasUrlParameter<String> {

    private String id_graphic;
    private GraphicController graphicController;
    private ChartGenerator chartGenerator;
    private QueryController queryController;
    HttpServletResponse HttpServletResponse;
    Graphic graphic;
    @Override
    public void setParameter(BeforeEvent beforeEvent, String dashboard) {
        for (String s:beforeEvent.getLocation().getSegments()
        ) {
            System.out.println("AUXILIO ------------>"+s);
        }
        this.id_graphic = beforeEvent.getLocation().getSegments().get(2);
        graphic = graphicController.findGraphicByIdGraphic( Integer.parseInt(id_graphic));
        generarGraphic();
    }

    @Autowired
    public GraphicShowView(GraphicController graphicController, ChartGenerator chartGenerator,
                           QueryController queryController, HttpServletResponse httpServletResponse){
        this.graphicController = graphicController;
        this.chartGenerator = chartGenerator;
        this.queryController = queryController;
        this.HttpServletResponse = httpServletResponse;
    }

    public void generarGraphic(){
        MenuBar menuBar = crearMenu();
        add(menuBar);

        Chart chart = TypeGraphic();

        FormLayout buttonsLayout = new FormLayout();
        buttonsLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("50em", 1),
                new FormLayout.ResponsiveStep("50em", 2));

        Button delete = new Button("Borrar");
        delete.addClickListener(e -> {
            graphicController.deleteGraph(Integer.parseInt(id_graphic));
            delete.getUI().ifPresent(ui ->
                    ui.navigate(""));
        });

        buttonsLayout.add(delete);

        Label description = new Label();
        description.add(new Text(queryController.findQuerybibyIdQuery(graphic.getIdQuerybi().getIdQuerybi()).getQuery()));

        setHorizontalComponentAlignment(Alignment.CENTER,description);
        setHorizontalComponentAlignment(Alignment.CENTER,chart);
        setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);

        add(chart);
        add(description);
        add(buttonsLayout);

    }

    private MenuBar crearMenu() {
        Label graficasLabel = new Label();
        graficasLabel.add(new Icon(VaadinIcon.CHART_LINE));
        graficasLabel.add(new Text("Graficas"));


        Label dashboardsLabel = new Label();
        dashboardsLabel.add(new Icon(VaadinIcon.DASHBOARD));
        dashboardsLabel.add(new Text("Dashboards"));


        Button signOutButton = new Button("Sign Out", new Icon(VaadinIcon.SIGN_OUT));
        signOutButton.setIconAfterText(true);
        signOutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        setHorizontalComponentAlignment(Alignment.END ,signOutButton);


        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        MenuItem graficas = menuBar.addItem(graficasLabel);
        MenuItem dashboards = menuBar.addItem(dashboardsLabel);
        MenuItem signOut = menuBar.addItem(signOutButton);

        graficas.getSubMenu().addItem(new RouterLink("Crear", GraphicNewView.class));
        graficas.getSubMenu().addItem(new RouterLink("Mostrar", GraphicsView.class));

        dashboards.getSubMenu().addItem(new RouterLink("Crear", DashboardNewView.class));
        dashboards.getSubMenu().addItem(new RouterLink("Mostrar", DashboardsView.class));

        signOut.addClickListener(e -> {
            System.out.println("ALGO");
            try {
                graphicController.localRedirect(HttpServletResponse);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY , MenuBarVariant.LUMO_ICON , MenuBarVariant.LUMO_LARGE);

        return menuBar;
    }


    private Chart TypeGraphic() {
        String type = "ChartType.AREASPLINE";
        chartGenerator.setGraphic(graphic);
        Chart chart = chartGenerator.GenerarGrafica();
        return chart;
    }

}
