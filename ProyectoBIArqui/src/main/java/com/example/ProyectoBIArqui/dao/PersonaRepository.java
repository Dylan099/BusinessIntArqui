package com.example.ProyectoBIArqui.dao;


import com.example.ProyectoBIArqui.domain.Estado;
import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.domain.Residencia1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonaRepository extends JpaRepository<Persona1,Integer> {
    List<Persona1> findAll();
    Persona1 findPersona1ByIdPersona(int pk);
    List<Persona1> findPersona1ByIdEstadoAndIdResidencia(Estado estado, Residencia1 residencia1);
}
