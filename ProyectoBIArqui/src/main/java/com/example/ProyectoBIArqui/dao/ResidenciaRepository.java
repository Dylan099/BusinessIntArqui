package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Departamento;
import com.example.ProyectoBIArqui.domain.Residencia1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenciaRepository extends JpaRepository<Residencia1,Integer> {
    Residencia1 findResidencia1ByIdDepartamento(Departamento departamento);
    Residencia1 findResidencia1ByIdResidencia(int pk);
}
