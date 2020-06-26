package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    List<Dashboard> findAll();
    Dashboard findDashboardByIdDashboard(int pk);
    @Query(value = "SELECT TOP 1 id_dashboard FROM dashboard ORDER BY id_dashboard DESC;", nativeQuery = true)
    Integer findLastId();
}
