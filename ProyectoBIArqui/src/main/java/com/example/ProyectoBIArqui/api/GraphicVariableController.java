package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.GraphicVariableBl;
import com.example.ProyectoBIArqui.domain.GraphicVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphicVariableController {
    GraphicVariableBl graphicVariableBl;

    @Autowired
    public GraphicVariableController(GraphicVariableBl graphicVariableBl) {
        this.graphicVariableBl = graphicVariableBl;
    }

    public GraphicVariable findGV(int pk){return graphicVariableBl.findGV(pk);}
}
