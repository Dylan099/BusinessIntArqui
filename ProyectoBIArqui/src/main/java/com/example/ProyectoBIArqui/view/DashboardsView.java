package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicDashboardController;
import com.example.ProyectoBIArqui.api.UserController;
import com.example.ProyectoBIArqui.domain.Dashboard;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.example.ProyectoBIArqui.domain.Userbi;
import com.example.ProyectoBIArqui.dto.DashboardDto;
import com.vaadin.flow.component.Text;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Route("dashboards")
@Theme(value = Material.class)
public class DashboardsView extends VerticalLayout {

    GraphicController graphicController;
    GraphicDashboardController graphicDashboardController;
    DashboardController dashboardController;
    UserController userController;
    HttpServletResponse HttpServletResponse;
    @Autowired
    public DashboardsView(GraphicController graphicController,
            GraphicDashboardController graphicDashboardController,
            DashboardController dashboardController, UserController userController, HttpServletResponse httpServletResponse){
        this.graphicController = graphicController;
        this.graphicDashboardController = graphicDashboardController;
        this.dashboardController = dashboardController;
        this.userController = userController;
        this.HttpServletResponse = httpServletResponse;
        MenuBar menuBar = crearMenu();
        add(menuBar);

        Grid<DashboardDto> grid = gridAddDashboards();
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


    private Grid<DashboardDto> gridAddDashboards() {

        Grid<DashboardDto> grid = new Grid<>();
        List<DashboardDto> dashboardDtoList = new ArrayList<>();
        grid.addColumn(DashboardDto::getIdDashboard).setHeader("IdDashboard");
        grid.addColumn(DashboardDto::getName).setHeader("name");
        grid.addColumn(DashboardDto::getDescription).setHeader("Description");

        grid.addItemClickListener(e -> {
            grid.getUI().ifPresent(ui ->
                    ui.navigate("dashboard/show/"+e.getItem().getIdDashboard()));
        });
        List<Integer> graphicDashboards = graphicDashboardController.findDashboardForView();
        List<Dashboard> dashboardList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Userbi userbi = userController.findUserByUsername(graphicController.wasoCasco(authentication));
        System.out.println("ID USUARIO ACTUAL: "+userbi.getIdUserbi());
        boolean flag = false;
        for (Integer gd: graphicDashboards
             ) {
            flag = false;
            Dashboard dashboard = dashboardController.findDashboardByIdDashboard(gd);
            List<GraphicDashboard> graphicDashboard = graphicDashboardController.findGraphicsByDashboard(dashboard);
            for (GraphicDashboard g:graphicDashboard
                 ) {
                System.out.println("ID GRAFICO: "+ g.getIdGraphic().getIdGraphic());
                Graphic graphic = graphicController.findGraphicByIdGraphic(g.getIdGraphic().getIdGraphic());
                if(graphic.getIdUserbi().getIdUserbi().equals(userbi.getIdUserbi())){
                    flag = !flag;
                }
            }
            if(flag)
                dashboardList.add(dashboard);
        }
        for (Dashboard d:dashboardList
             ) {
            dashboardDtoList.add(new DashboardDto(d.getIdDashboard(),d.getName(),d.getDescription()));
        }
        grid.setItems(dashboardDtoList);
        return grid;
    }



}
