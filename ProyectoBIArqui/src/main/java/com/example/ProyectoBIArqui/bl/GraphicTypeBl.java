package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.GraphicTypeRepository;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicType;
import com.example.ProyectoBIArqui.domain.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphicTypeBl {
    GraphicTypeRepository graphicTypeRepository;

    @Autowired
    public GraphicTypeBl(GraphicTypeRepository graphicTypeRepository) {
        this.graphicTypeRepository = graphicTypeRepository;
    }

    public List<String> findAllGT(){
        List<GraphicType> graphicTypes = graphicTypeRepository.findAll();
        List<String> list = new ArrayList<>();
        for (GraphicType gt:graphicTypes
             ) {
            list.add(gt.getType());
        }
        return list;
    }
}
