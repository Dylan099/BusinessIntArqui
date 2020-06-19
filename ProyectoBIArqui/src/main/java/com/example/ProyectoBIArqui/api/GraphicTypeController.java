package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.GraphicTypeBl;
import com.example.ProyectoBIArqui.domain.GraphicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphicTypeController {
    GraphicTypeBl graphicTypeBl;

    @Autowired
    public GraphicTypeController(GraphicTypeBl graphicTypeBl) {
        this.graphicTypeBl = graphicTypeBl;
    }

    public GraphicType findGraphicType(int pk){return graphicTypeBl.findGraphicType(pk);}

    public GraphicType findGraphicTypeByType(String type){return graphicTypeBl.findGraphicTypeByType(type);}
}
