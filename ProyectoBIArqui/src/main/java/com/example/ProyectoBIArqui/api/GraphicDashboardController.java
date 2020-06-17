package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.GraphicDashboardBl;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphicDashboardController {
    GraphicDashboardBl graphicDashboardBl;

    @Autowired
    public GraphicDashboardController(GraphicDashboardBl graphicDashboardBl) {
        this.graphicDashboardBl = graphicDashboardBl;
    }

    public List<GraphicDashboard> findDistinctDashboardByIdGraphic(int graphicId){return graphicDashboardBl.findDashboardsByGraphics(graphicId);}

    public List<Integer> findDashboardForView()
    {
        return graphicDashboardBl.findDashboardsByQueryPro();
    }
}
