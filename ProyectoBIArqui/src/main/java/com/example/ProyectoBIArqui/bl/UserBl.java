package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.UserRepository;
import com.example.ProyectoBIArqui.domain.Userbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBl {
    UserRepository userRepository;

    @Autowired
    public UserBl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Userbi> findAll(){
        return userRepository.findAll();
    }
}
