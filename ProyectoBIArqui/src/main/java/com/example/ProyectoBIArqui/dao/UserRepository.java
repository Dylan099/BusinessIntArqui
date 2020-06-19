package com.example.ProyectoBIArqui.dao;

import com.example.ProyectoBIArqui.domain.Userbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Userbi,Integer> {
    List<Userbi> findAll();
    Userbi findUserbiByIdUserbi(int pk);
    Userbi findUserbiByUsername(String s);
}
