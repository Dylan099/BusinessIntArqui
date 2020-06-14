package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.Query;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("graphic/new")
@Theme(value = Material.class)
public class GraphicNewView extends VerticalLayout {
    GraphicController graphicController;

    @Autowired
    public GraphicNewView(GraphicController graphicController){
        this.graphicController =  graphicController;
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
            System.out.println("AUXILIO--------------------->---------->"+ query.getValue());
            nuevaGrafica.setTitulo(title.getValue());
            nuevaGrafica.setDesc(description.getValue());
            nuevaGrafica.setGraphicType(type.getValue());
            nuevaGrafica.setQuery(query.getValue());
            nuevaGrafica.setVarInd(variable.getValue());

            add(this.graphicController.generarGrafica(nuevaGrafica));
            //TODO: GUARDAR GRAFICA EN LA BDD
            /*save.getUI().ifPresent(ui ->
                    ui.navigate("graphic/waso"));*/
        });

        Chart waso = this.graphicController.generarGrafica(nuevaGrafica);

        setHorizontalComponentAlignment(Alignment.CENTER,titleClass);
        setHorizontalComponentAlignment(Alignment.CENTER,title);
        setHorizontalComponentAlignment(Alignment.CENTER,query);
        setHorizontalComponentAlignment(Alignment.CENTER,type);
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
        MenuBar menuBar = new MenuBar();

        menuBar.setOpenOnHover(true);

        Text selected = new Text("");
        Div message = new Div(new Text("Selected: "), selected);

        MenuItem graficas = menuBar.addItem("Graficas");
        MenuItem dashboards = menuBar.addItem("Dashboards");
        MenuItem informes = menuBar.addItem("Informes");
        menuBar.addItem("Sign Out", e -> selected.setText("Sign Out"));

        graficas.getSubMenu().addItem("Crear",
                e -> selected.setText("Crear"));
        graficas.getSubMenu().addItem("Mostrar",
                e -> selected.setText("Mostrar"));

        dashboards.getSubMenu().addItem("Crear",
                e -> selected.setText("Crear"));
        dashboards.getSubMenu().addItem("Mostrar",
                e -> selected.setText("Mostrar"));

        informes.getSubMenu().addItem("Generar",
                e -> selected.setText("Generar"));
        informes.getSubMenu().addItem("Mostrar",
                e -> selected.setText("Mostrar"));

        return menuBar;
    }

}
