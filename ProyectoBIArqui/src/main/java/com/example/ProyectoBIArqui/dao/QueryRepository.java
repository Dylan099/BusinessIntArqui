package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Querybi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Querybi,Integer> {
    List<Querybi> findAll();
    Querybi findQuerybiByQuery(String query);
    Querybi findQuerybiByIdQuerybi(int pk);
}
