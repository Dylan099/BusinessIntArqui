package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.QueryRepository;
import com.example.ProyectoBIArqui.domain.Query;
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
        List<Query> objQueries = queryRepository.findAll();
        List<String> queries = new ArrayList<>();
        for (Query q:objQueries
             ) {
            queries.add(q.getQuery());
        }
        return queries;
    }
}
