package com.example.ProyectoBIArqui.view;

import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicTypeController;
import com.example.ProyectoBIArqui.api.GraphicVariableController;
import com.example.ProyectoBIArqui.api.QueryController;
import com.example.ProyectoBIArqui.bl.QueryBl;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicType;
import com.example.ProyectoBIArqui.domain.GraphicVariable;
import com.example.ProyectoBIArqui.domain.Querybi;
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
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("graphics")
@Theme(value = Material.class)

public class GraphicsView extends VerticalLayout {
    GraphicController graphicController;
    QueryController queryController;
    GraphicTypeController graphicTypeController;
    GraphicVariableController graphicVariableController;

    @Autowired
    public GraphicsView(GraphicController graphicController, QueryController queryController,
    GraphicTypeController graphicTypeController, GraphicVariableController graphicVariableController){
        this.graphicController = graphicController;
        this.queryController = queryController;
        this.graphicTypeController = graphicTypeController;
        this.graphicVariableController = graphicVariableController;
        MenuBar menuBar = crearMenu();
        add(menuBar);

        Grid<GraphicDto> grid = gridAddGraophics();
        add(grid);

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

    private Grid<GraphicDto> gridAddGraophics() {

        Grid<GraphicDto> grid = new Grid<>();
        List<GraphicDto> graphicDtoList = new ArrayList<>();
        grid.addColumn(GraphicDto::getIdGraphic).setHeader("idGraphic");
        grid.addColumn(GraphicDto::getName).setHeader("name");
        grid.addColumn(GraphicDto::getQuery).setHeader("query");
        grid.addColumn(GraphicDto::getDescription).setHeader("description");
        grid.addItemClickListener(e -> {
            GraphicConfig graphicConfig = new GraphicConfig();
            graphicConfig.setTitulo(e.getItem().getName());
            graphicConfig.setDesc(e.getItem().getDescription());
            graphicConfig.setGraphicType(graphicTypeController.findGraphicType(e.getItem().getIdGraphicType()).getType());
            graphicConfig.setQuery(queryController.findQuerybibyIdQuery(e.getItem().getIdQuery()).getQuery());
            graphicConfig.setVarInd(graphicVariableController.findGV(e.getItem().getIdGraphicVariable()).getVariable());
            Chart chart = this.graphicController.generarGrafica(graphicConfig);
            add(chart);
        });
        //TODO BUSCAR POR USUARIO
        List<Graphic> graphicList = graphicController.findAllByUserId(1);
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
