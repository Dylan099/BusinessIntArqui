package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    List<Dashboard> findAll();
    Dashboard findDashboardByIdDashboard(int pk);
}
