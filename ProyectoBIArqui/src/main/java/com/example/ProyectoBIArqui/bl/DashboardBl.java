package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.DashboardRepository;
import com.example.ProyectoBIArqui.dao.GraphicDashboardRepository;
import com.example.ProyectoBIArqui.domain.Dashboard;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.example.ProyectoBIArqui.dto.DashboardConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DashboardBl {
    DashboardRepository dashboardRepository;
    GraphicDashboardRepository graphicDashboardRepository;

    @Autowired
    public DashboardBl(DashboardRepository dashboardRepository, GraphicDashboardRepository graphicDashboardRepository) {
        this.dashboardRepository = dashboardRepository;
        this.graphicDashboardRepository = graphicDashboardRepository;
    }

    public void saveDasboard(DashboardConfig dashboardConfig)
    {
        Dashboard dashboard = new Dashboard();
        int id_dash = dashboardRepository.findAll().size()+1;
        dashboard.setIdDashboard(id_dash);
        dashboard.setDescription(dashboardConfig.getDesc());
        dashboard.setName(dashboardConfig.getName());
        dashboard.setTDate(new Date());
        dashboard.setTxHost("Host");
        dashboard.setTxUser("User");
        dashboardRepository.save(dashboard);
        for (Graphic g:dashboardConfig.getGraphicList()
             ) {
            GraphicDashboard graphicDashboard = new GraphicDashboard();
            graphicDashboard.setIdGraphicDashboard(graphicDashboardRepository.findAll().size()+1);
            graphicDashboard.setIdGraphic(g);
            graphicDashboard.setIdDashboard(dashboard);
            graphicDashboard.setTxDate(new Date());
            graphicDashboard.setTxHost("Host");
            graphicDashboard.setTxUser("User");
            graphicDashboardRepository.save(graphicDashboard);
        }
    }

    public Dashboard findDashboardByIdDashboard(int pk)
    {
        return dashboardRepository.findDashboardByIdDashboard(pk);
    }
}
