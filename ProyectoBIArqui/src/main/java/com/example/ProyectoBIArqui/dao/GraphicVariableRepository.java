package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.GraphicVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphicVariableRepository extends JpaRepository<GraphicVariable,Integer> {
    List<GraphicVariable> findAll();
}
