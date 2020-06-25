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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Route("dashboards")
@Theme(value = Lumo.class,variant = Lumo.DARK)
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

        grid.appendHeaderRow();
        grid.setThemeName(Lumo.DARK);
        grid.setHeightByRows(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,GridVariant.MATERIAL_COLUMN_DIVIDERS,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        return grid;
    }



}
