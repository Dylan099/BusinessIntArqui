package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicDashboardController;
import com.example.ProyectoBIArqui.bl.ChartGenerator;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Route("dashboard/show")
@Theme(value = Material.class)
public class DashboardShowView extends VerticalLayout implements HasUrlParameter<String> {

    private String id_dashboard;
    private DashboardController dashboardController;
    private GraphicDashboardController graphicDashboardController;
    private GraphicController graphicController;
    private ChartGenerator chartGenerator;
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
    ,GraphicController graphicController, ChartGenerator chartGenerator){
        this.dashboardController = dashboardController;
        this.graphicDashboardController = graphicDashboardController;
        this.graphicController = graphicController;
        this.chartGenerator = chartGenerator;

    }
    static {

        System.setProperty("java.awt.headless", "false");
    }

    private void generarDashboard()
    {
        System.out.println("PLS ---------------->" + id_dashboard);
        MenuBar menuBar = crearMenu();
        add(menuBar);

        H1 title = new H1();
        title.add(new Text("Titulo del dashboard"));

        Label description = new Label();
        description.add(new Text("Descripcion del dashboard"));

        Board board = boardAddGraphics();

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
            Robot robot = null;
            try {
                robot = new Robot();
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage capture = robot.createScreenCapture(screenRect);
                File outputfile = new File("image.jpg");
                ImageIO.write(capture, "jpg", outputfile);
            } catch (AWTException | IOException ex) {
                ex.printStackTrace();
            }
        });

        buttonsLayout.add(edit, delete);


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

}
