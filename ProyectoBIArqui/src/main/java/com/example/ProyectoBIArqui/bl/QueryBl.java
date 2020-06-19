package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.QueryRepository;
import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.domain.Querybi;
import com.example.ProyectoBIArqui.dto.QueryXY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryBl {

    QueryRepository queryRepository;

    @Autowired
    public QueryBl(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public List<String> findAllQueries(){
        List<Querybi> objQueries = queryRepository.findAll();
        List<String> queries = new ArrayList<>();
        for (Querybi q:objQueries
             ) {
            queries.add(q.getQuery());
        }
        return queries;
    }

    public Querybi findQuerybi(int pk)
    {
        return queryRepository.findQuerybiByIdQuerybi(pk);
    }

    public Querybi findQuerybyQuery(String query){return queryRepository.findQuerybiByQuery(query);}
}
