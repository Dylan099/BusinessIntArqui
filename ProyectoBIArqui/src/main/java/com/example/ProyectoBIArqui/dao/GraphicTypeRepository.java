package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.GraphicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphicTypeRepository extends JpaRepository<GraphicType, Integer> {
    List<GraphicType> findAll();
    GraphicType findGraphicTypeByIdGraphicType(int pk);
}
