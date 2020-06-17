package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Graphic;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.domain.Userbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphicRepository extends JpaRepository<Graphic,Integer> {
    List<Graphic> findAllByIdUserbi(Userbi userbi);
    Graphic findGraphicByIdQuerybi(Querybi querybi);
    Graphic findGraphicByName(String name);
}
