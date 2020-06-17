package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.GraphicDashboardRepository;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphicDashboardBl {
    GraphicDashboardRepository graphicDashboardRepository;

    @Autowired
    public GraphicDashboardBl(GraphicDashboardRepository graphicDashboardRepository) {
        this.graphicDashboardRepository = graphicDashboardRepository;
    }

    public List<GraphicDashboard> findDashboardsByGraphics(int graphicId)
    {
        return graphicDashboardRepository.findByDistinctByIdGraphic(graphicId);
    }

    public List<Integer> findDashboardsByQueryPro()
    {
        return graphicDashboardRepository.QueryWaso();
    }
}
