package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.PersonaRepository;

import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaBl {
    PersonaRepository personaRepository;

    @Autowired
    public PersonaBl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona1> findAllPersona(){
        return new ArrayList<>(personaRepository.findAll());
    }

    public PersonaDto findPersonabyId(Integer pk){
        Persona1 newpersona = personaRepository.findPersona1ByIdPersona(pk);
        return new PersonaDto(newpersona);
    }

}
