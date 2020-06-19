package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Dashboard;
import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.GraphicDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphicDashboardRepository extends JpaRepository<GraphicDashboard,Integer> {
    List<GraphicDashboard> findAll();
    @Query( value  =  " SELECT  * FROM graphic_dashboard where id_graphic=?1 DISTINCT id_dashboard; " , nativeQuery  =  true )
    List<GraphicDashboard> findByDistinctByIdGraphic(int idGraphic);
    @Query(value = " select distinct id_dashboard from graphic_dashboard inner join graphic on graphic_dashboard.id_graphic = graphic.id_graphic;", nativeQuery = true)
    List<Integer> QueryWaso();
    List<GraphicDashboard> findAllByIdDashboard(Dashboard dashboard);
    List<GraphicDashboard> findAllByIdGraphic(Graphic graphic);
}
