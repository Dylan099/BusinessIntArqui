package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {
    Departamento findDepartamentoByIdDepartamento(int pk);
}
