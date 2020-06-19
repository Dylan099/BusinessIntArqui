package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.QueryBl;
import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.dto.QueryXY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryController {
    QueryBl queryBl;

    @Autowired
    public QueryController(QueryBl queryBl) {
        this.queryBl = queryBl;
    }

    public Querybi findQuerybibyIdQuery(int pk){
        return queryBl.findQuerybi(pk);
    }

    public Querybi findQueryByQuery(String query){return queryBl.findQuerybyQuery(query);}

}
