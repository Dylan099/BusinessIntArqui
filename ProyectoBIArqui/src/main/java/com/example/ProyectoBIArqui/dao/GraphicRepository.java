package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Graphic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicRepository extends JpaRepository<Graphic,Integer> {
}
