package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.DashboardBl;
import com.example.ProyectoBIArqui.bl.GraphicDashboardBl;
import com.example.ProyectoBIArqui.domain.Dashboard;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import com.example.ProyectoBIArqui.dto.DashboardConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardController {
    DashboardBl dashboardBl;

    @Autowired
    public DashboardController(DashboardBl dashboardBl) {
        this.dashboardBl = dashboardBl;
    }

    public Dashboard findDashboardByIdDashboard(int pk)
    {
        return dashboardBl.findDashboardByIdDashboard(pk);
    }

    public void SaveDashboard(DashboardConfig dashboardConfig)
    {
        dashboardBl.saveDasboard(dashboardConfig);
    }
}
