package com.example.ProyectoBIArqui.dao;


import com.example.ProyectoBIArqui.domain.Persona1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonaRepository extends JpaRepository<Persona1,Integer> {
    List<Persona1> findAll();
    Persona1 findPersona1ByIdPersona(int pk);
}
