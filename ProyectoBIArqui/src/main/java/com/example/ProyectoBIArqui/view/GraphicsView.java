package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.*;
import com.example.ProyectoBIArqui.bl.QueryBl;
import com.example.ProyectoBIArqui.dao.UserRepository;
import com.example.ProyectoBIArqui.domain.*;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.example.ProyectoBIArqui.dto.GraphicDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Route("")
@Theme(value = Lumo.class,variant = Lumo.DARK)

public class GraphicsView extends VerticalLayout {
    GraphicController graphicController;
    QueryController queryController;
    GraphicTypeController graphicTypeController;
    GraphicVariableController graphicVariableController;
    HttpServletResponse HttpServletResponse;
    UserController userController;

    @Autowired
    public GraphicsView(GraphicController graphicController, QueryController queryController,
    GraphicTypeController graphicTypeController, GraphicVariableController graphicVariableController,
                        HttpServletResponse httpServletResponse, UserController userController){
        this.graphicController = graphicController;
        this.queryController = queryController;
        this.graphicTypeController = graphicTypeController;
        this.graphicVariableController = graphicVariableController;
        this.HttpServletResponse = httpServletResponse;
        this.userController = userController;
        MenuBar menuBar = crearMenu();
        add(menuBar);
        Grid<GraphicDto> grid = gridAddGraophics();
        add(grid);

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

    private Grid<GraphicDto> gridAddGraophics() {

        Grid<GraphicDto> grid = new Grid<>();
        List<GraphicDto> graphicDtoList = new ArrayList<>();
        grid.addColumn(GraphicDto::getIdGraphic).setHeader("idGraphic");
        grid.addColumn(GraphicDto::getName).setHeader("name");
        grid.addColumn(GraphicDto::getQuery).setHeader("query");
        grid.addColumn(GraphicDto::getDescription).setHeader("description");
        grid.addItemClickListener(e -> {
           grid.getUI().ifPresent(ui ->
              ui.navigate("graphic/show/"+e.getItem().getIdGraphic()));
        });
  /*
            GraphicConfig graphicConfig = new GraphicConfig();
            graphicConfig.setTitulo(e.getItem().getName());
            graphicConfig.setDesc(e.getItem().getDescription());
            graphicConfig.setGraphicType(graphicTypeController.findGraphicType(e.getItem().getIdGraphicType()).getType());
            graphicConfig.setQuery(queryController.findQuerybibyIdQuery(e.getItem().getIdQuery()).getQuery());
            graphicConfig.setVarInd(graphicVariableController.findGV(e.getItem().getIdGraphicVariable()).getVariable());
            Chart chart = this.graphicController.generarGrafica(graphicConfig);
            add(chart);
        });
*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Userbi userbi = userController.findUserByUsername(graphicController.wasoCasco(authentication));
        System.out.println("ID USUARIO ACTUAL: "+userbi.getIdUserbi());
        List<Graphic> graphicList = graphicController.findAllByUserId(userbi.getIdUserbi());
        for (Graphic g:graphicList
             ) {
            Querybi query= queryController.findQuerybibyIdQuery(g.getIdQuerybi().getIdQuerybi());
            GraphicType type = graphicTypeController.findGraphicType(g.getIdGraphicType().getIdGraphicType());
            GraphicDto temp = new GraphicDto(g.getIdGraphic(),g.getName(),query.getQuery(),type.getType(),g.getDescription()
            ,g.getIdUserbi().getIdUserbi(),g.getIdQuerybi().getIdQuerybi(),g.getIdGraphicType().getIdGraphicType(),g.getIdGraphicVariable().getIdGraphicVariable());
            graphicDtoList.add(temp);
        }
        grid.setItems(graphicDtoList);

        grid.appendHeaderRow();
        grid.setThemeName(Lumo.DARK);
        grid.setHeightByRows(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,GridVariant.MATERIAL_COLUMN_DIVIDERS,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        return grid;
    }



}
