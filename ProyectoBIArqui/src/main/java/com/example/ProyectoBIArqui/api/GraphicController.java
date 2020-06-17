package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.GraphicBl;
import com.example.ProyectoBIArqui.bl.GraphicTypeBl;
import com.example.ProyectoBIArqui.bl.GraphicVariableBl;
import com.example.ProyectoBIArqui.bl.QueryBl;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.vaadin.flow.component.charts.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
public class GraphicController {

    private QueryBl queryBl;
    private GraphicTypeBl graphicTypeBl;
    private GraphicVariableBl graphicVariableBl;
    private GraphicBl graphicBl;

    @Autowired
    public GraphicController(QueryBl queryBl, GraphicTypeBl graphicTypeBl, GraphicVariableBl graphicVariableBl, GraphicBl graphicBl) {
        this.queryBl = queryBl;
        this.graphicTypeBl = graphicTypeBl;
        this.graphicVariableBl = graphicVariableBl;
        this.graphicBl = graphicBl;
    }

    public Chart generarGrafica(GraphicConfig graphicConfig)
    {
        if(graphicConfig!=null){
            return graphicBl.generateGraphic(graphicConfig);
        }
        return null;
    }

    public void saveGrafica(GraphicConfig graphicConfig)
    {
        graphicBl.saveGraphic(graphicConfig);
    }

    public List<Graphic> findAllByUserId(int pk){return graphicBl.findAllGraphics(pk);}

    public List<String> FindAllQueries(){
        return queryBl.findAllQueries();
    }

    public List<String> FindAllGraphicType(){
        return graphicTypeBl.findAllGT();
    }

    public List<String> FindAllVariables(){
        return graphicVariableBl.findAllVariables();
    }

    public Graphic FindGraphicByQuery(Querybi querybi){return graphicBl.findGraphicByQuery(querybi);}

    public Graphic findGraphicByName(String name){return graphicBl.findGraphicByName(name);}

    public String wasoCasco(Authentication authentication){return authentication.getName();}
}
