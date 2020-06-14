package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.GraphicBl;
import com.example.ProyectoBIArqui.bl.GraphicTypeBl;
import com.example.ProyectoBIArqui.bl.GraphicVariableBl;
import com.example.ProyectoBIArqui.bl.QueryBl;
import com.example.ProyectoBIArqui.domain.GraphicType;
import com.example.ProyectoBIArqui.domain.Query;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.vaadin.flow.component.charts.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    @RequestMapping(value = "graphic/waso", method = RequestMethod.GET)
    public Chart generarGrafica(GraphicConfig graphicConfig)
    {
        if(graphicConfig!=null){
            return graphicBl.generateGraphic(graphicConfig);
        }
        return null;
    }

    public List<String> FindAllQueries(){
        return queryBl.findAllQueries();
    }

    public List<String> FindAllGraphicType(){
        return graphicTypeBl.findAllGT();
    }

    public List<String> FindAllVariables(){
        return graphicVariableBl.findAllVariables();
    }
}
