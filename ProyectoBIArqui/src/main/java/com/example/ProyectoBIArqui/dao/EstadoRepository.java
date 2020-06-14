package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
    Estado findEstadoByIdEstado(int pk);
}
