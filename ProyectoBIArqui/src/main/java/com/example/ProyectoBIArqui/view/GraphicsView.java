package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.*;
import com.example.ProyectoBIArqui.bl.QueryBl;
import com.example.ProyectoBIArqui.dao.UserRepository;
import com.example.ProyectoBIArqui.domain.*;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.example.ProyectoBIArqui.dto.GraphicDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
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
@Theme(value = Material.class)

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
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        MenuItem graficas = menuBar.addItem("Graficas");
        MenuItem dashboards = menuBar.addItem("Dashboards");
        MenuItem signOut = menuBar.addItem("Sign Out");

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
        return grid;
    }



}
