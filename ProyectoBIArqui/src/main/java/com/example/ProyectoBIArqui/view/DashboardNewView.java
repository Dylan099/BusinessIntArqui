package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.DashboardController;
import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.QueryController;
import com.example.ProyectoBIArqui.api.UserController;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.domain.Userbi;
import com.example.ProyectoBIArqui.dto.DashboardConfig;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Route("dashboard/new")
@Theme(value = Lumo.class,variant = Lumo.DARK)
public class DashboardNewView extends VerticalLayout {

    GraphicController graphicController;
    QueryController queryController;
    DashboardController dashboardController;
    HttpServletResponse HttpServletResponse;
    UserController userController;

    public DashboardNewView(GraphicController graphicController, QueryController queryController
    ,DashboardController dashboardController, HttpServletResponse httpServletResponse, UserController userController){
        this.graphicController = graphicController;
        this.queryController = queryController;
        this.dashboardController = dashboardController;
        this.HttpServletResponse = httpServletResponse;
        this.userController = userController;
        MenuBar menuBar = crearMenu();
        add(menuBar);

        H1 titleClass = new H1();
        titleClass.add(new Text("Crear nuevo dashboard"));

        TextArea title = new TextArea("Titulo del dashboard");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Userbi userbi = userController.findUserByUsername(graphicController.wasoCasco(authentication));
        System.out.println("ID USUARIO ACTUAL: "+userbi.getIdUserbi());
        List<Graphic> graphicList = graphicController.findAllByUserId(userbi.getIdUserbi());
        MultiSelectListBox<String> checkboxGroupGrapgics = new MultiSelectListBox<>();

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
