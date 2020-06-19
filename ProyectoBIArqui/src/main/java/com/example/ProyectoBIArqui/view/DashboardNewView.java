package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.QueryController;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.dto.DashboardConfig;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Route("dashboard/new")
@Theme(value = Material.class)
public class DashboardNewView extends VerticalLayout {

    GraphicController graphicController;
    QueryController queryController;
    DashboardController dashboardController;
    HttpServletResponse HttpServletResponse;

    public DashboardNewView(GraphicController graphicController, QueryController queryController
    ,DashboardController dashboardController, HttpServletResponse httpServletResponse){
        this.graphicController = graphicController;
        this.queryController = queryController;
        this.dashboardController = dashboardController;
        this.HttpServletResponse = httpServletResponse;
        MenuBar menuBar = crearMenu();
        add(menuBar);

        H1 titleClass = new H1();
        titleClass.add(new Text("Crear nuevo dashboard"));

        TextArea title = new TextArea("Titulo del dashboard");

        //TODO: RECUPERAR GRAFICAS POR USUARIO
        List<Graphic> graphicList = graphicController.findAllByUserId(1);
        CheckboxGroup<String> checkboxGroupGrapgics = new CheckboxGroup<>();
        String[] graficas = new String[graphicList.size()];
        for (int i = 0; i < graphicList.size(); i++) {
            graficas[i] = graphicList.get(i).getName()+"\n";
        }
        checkboxGroupGrapgics.setItems(graficas);
        TextArea description = new TextArea("Descripcion de la grafica");

        Button save = new Button("Guardar");
        save.addClickListener(e -> {
            Set<String> n = checkboxGroupGrapgics.getSelectedItems();
            List<Graphic> graphics = new ArrayList<>();
            for (String s:n
                 ) {
                graphics.add(graphicController.findGraphicByName(s));
            }
            DashboardConfig dashboardConfig = new DashboardConfig();
            dashboardConfig.setName(title.getValue());
            dashboardConfig.setDesc(description.getValue());
            dashboardConfig.setGraphicList(graphics);
            dashboardController.SaveDashboard(dashboardConfig);
            save.getUI().ifPresent(ui ->
                    ui.navigate("dashboards"));
        });

        setHorizontalComponentAlignment(Alignment.CENTER,titleClass);
        setHorizontalComponentAlignment(Alignment.CENTER,title);
        setHorizontalComponentAlignment(Alignment.CENTER,checkboxGroupGrapgics);
        setHorizontalComponentAlignment(Alignment.CENTER,description);
        setHorizontalComponentAlignment(Alignment.CENTER,save);

        add(titleClass);
        add(title);
        add(checkboxGroupGrapgics);
        add(description);
        add(save);


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

}
