package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.GraphicVariableRepository;
import com.example.ProyectoBIArqui.domain.GraphicType;
import com.example.ProyectoBIArqui.domain.GraphicVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphicVariableBl {

    GraphicVariableRepository graphicVariableRepository;

    @Autowired
    public GraphicVariableBl(GraphicVariableRepository graphicVariableRepository) {
        this.graphicVariableRepository = graphicVariableRepository;
    }

    public List<String> findAllVariables()
    {
        List<GraphicVariable> graphicVariables = graphicVariableRepository.findAll();
        List<String> variables = new ArrayList<>();
        for (GraphicVariable gv:graphicVariables
             ) {
            variables.add(gv.getVariable());
        }
        return variables;
    }

    public GraphicVariable findGV(int pk)
    {
        return graphicVariableRepository.findGraphicVariableByIdGraphicVariable(pk);
    }

}
