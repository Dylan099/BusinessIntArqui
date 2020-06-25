package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicDashboardController;
import com.example.ProyectoBIArqui.bl.ChartGenerator;
import com.example.ProyectoBIArqui.domain.Dashboard;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import net.minidev.json.JSONArray;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static javax.imageio.ImageIO.read;

@Route("dashboard/show")
@Theme(value = Lumo.class,variant = Lumo.DARK)
public class DashboardShowView extends VerticalLayout implements HasUrlParameter<String> {

    private String id_dashboard;
    private DashboardController dashboardController;
    private GraphicDashboardController graphicDashboardController;
    private GraphicController graphicController;
    private ChartGenerator chartGenerator;
    HttpServletResponse httpServletResponse;
    @Override
    public void setParameter(BeforeEvent beforeEvent, String dashboard) {
        for (String s:beforeEvent.getLocation().getSegments()
             ) {
            System.out.println("AUXILIO ------------>"+s);
        }
        this.id_dashboard = beforeEvent.getLocation().getSegments().get(2);
        generarDashboard();
    }

    @Autowired
    public DashboardShowView(DashboardController dashboardController, GraphicDashboardController graphicDashboardController
    ,GraphicController graphicController, ChartGenerator chartGenerator, HttpServletResponse httpServletResponse){
        this.dashboardController = dashboardController;
        this.graphicDashboardController = graphicDashboardController;
        this.graphicController = graphicController;
        this.chartGenerator = chartGenerator;
        this.httpServletResponse = httpServletResponse;
    }
    static {

        System.setProperty("java.awt.headless", "false");
    }

    private void generarDashboard()
    {
        System.out.println("PLS ---------------->" + id_dashboard);
        MenuBar menuBar = crearMenu();
        add(menuBar);
        Dashboard dashboard = dashboardController.findDashboardByIdDashboard(Integer.parseInt(id_dashboard));
        H1 title = new H1();
        title.add(new Text(dashboard.getName()));

        Label description = new Label();
        description.add(new Text(dashboard.getDescription()));

        Board board = boardAddGraphics();

        FormLayout buttonsLayout = new FormLayout();
        buttonsLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("50em", 1),
                new FormLayout.ResponsiveStep("50em", 2));


        Button delete = new Button("Borrar");
        delete.addClickListener(e -> {
            dashboardController.deleteDashboard(Integer.parseInt(id_dashboard));
            delete.getUI().ifPresent(ui ->
                    ui.navigate("dashboards"));
        });

        buttonsLayout.add(delete);


        setHorizontalComponentAlignment(Alignment.CENTER,title);
        setHorizontalComponentAlignment(Alignment.CENTER,description);
        setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);

        add(title);
        add(board);
        add(description);
        add(buttonsLayout);
    }

    private Board boardAddGraphics() {
        Board board = new Board();
        List<GraphicDashboard> graphicDashboards = graphicDashboardController.findGraphicsByDashboard(dashboardController.findDashboardByIdDashboard(Integer.parseInt(id_dashboard)));
        Div child = new Div();

        for (GraphicDashboard gd:graphicDashboards
             ) {
            Graphic graphic = graphicController.findGraphicByIdGraphic(gd.getIdGraphic().getIdGraphic());
            chartGenerator.setGraphic(graphic);
            child.add(chartGenerator.GenerarGrafica());
            board.addRow(child);
        }

        return board;
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
                graphicController.localRedirect(httpServletResponse);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY , MenuBarVariant.LUMO_ICON , MenuBarVariant.LUMO_LARGE);

        return menuBar;
    }

}
