package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
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
import java.util.List;

@Route("graphic/new")
@Theme(value = Lumo.class,variant = Lumo.DARK)
public class GraphicNewView extends VerticalLayout {
    GraphicController graphicController;
    HttpServletResponse HttpServletResponse;

    @Autowired
    public GraphicNewView(GraphicController graphicController, HttpServletResponse httpServletResponse){
        this.graphicController =  graphicController;
        this.HttpServletResponse = httpServletResponse;
        MenuBar menuBar = crearMenu();
        add(menuBar);

        H1 titleClass = new H1();
        titleClass.add(new Text("Crear nueva grafica"));

        TextArea title = new TextArea("Titulo de la grafica");

        ComboBox<String> query = new ComboBox<String>();
        query.setLabel("Querys");
        List<String> queriesList = this.graphicController.FindAllQueries();
        query.setItems(queriesList);
        query.setClearButtonVisible(true);

        ComboBox<String> type = new ComboBox<String>();
        type.setLabel("Type");
        List<String> gtList = this.graphicController.FindAllGraphicType();
        type.setItems(gtList);
        type.setClearButtonVisible(true);

        ComboBox<String > variable = new ComboBox<String>();
        variable.setLabel("Variable Independiente");
        List<String> vList = this.graphicController.FindAllVariables();
        variable.setItems(vList);
        variable.setClearButtonVisible(true);

        TextArea description = new TextArea("Descripcion de la grafica");

        GraphicConfig nuevaGrafica = new GraphicConfig();
        Button save = new Button("Guardar");
        save.addClickListener(e -> {
            nuevaGrafica.setTitulo(title.getValue());
            nuevaGrafica.setDesc(description.getValue());
            nuevaGrafica.setGraphicType(type.getValue());
            nuevaGrafica.setQuery(query.getValue());
            nuevaGrafica.setVarInd(variable.getValue());
            this.graphicController.saveGrafica(nuevaGrafica);
            save.getUI().ifPresent(ui ->
                    ui.navigate(""));
        });

        setHorizontalComponentAlignment(Alignment.CENTER,titleClass);
        setHorizontalComponentAlignment(Alignment.CENTER,title);
        setHorizontalComponentAlignment(Alignment.CENTER,query);
        setHorizontalComponentAlignment(Alignment.CENTER,type);
        setHorizontalComponentAlignment(Alignment.CENTER,variable);
        setHorizontalComponentAlignment(Alignment.CENTER,description);
        setHorizontalComponentAlignment(Alignment.CENTER,save);

        add(titleClass);
        add(title);
        add(query);
        add(type);
        add(description);
        add(save);
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

}
