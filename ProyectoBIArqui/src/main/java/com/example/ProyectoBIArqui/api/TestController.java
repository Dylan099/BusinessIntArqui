package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.PersonaBl;
import com.example.ProyectoBIArqui.dao.PersonaRepository;

import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    PersonaBl personaBl;

    @Autowired
    public TestController(PersonaBl personaBl) {
        this.personaBl = personaBl;
    }

    @RequestMapping(value = "/suicidio", method = RequestMethod.GET)
    List<Persona1> all()
    {
        return personaBl.findAllPersona();

    }

    @RequestMapping(value ="/suicidio2", method = RequestMethod.GET)
    String findPersonabyId(){
        return personaBl.findPersonabyId(1).toString();
    }
}
