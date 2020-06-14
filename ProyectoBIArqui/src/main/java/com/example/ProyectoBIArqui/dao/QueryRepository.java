package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Query,Integer> {
    List<Query> findAll();
}
