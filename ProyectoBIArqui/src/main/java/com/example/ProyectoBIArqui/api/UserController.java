package com.example.ProyectoBIArqui.api;

import com.example.ProyectoBIArqui.bl.UserBl;
import com.example.ProyectoBIArqui.domain.Userbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserController {
    UserBl userBl;

    @Autowired
    public UserController(UserBl userBl) {
        this.userBl = userBl;
    }

    public List<Userbi> findAll(){return userBl.findAll();}

    public Userbi findUserByUsername(String s){return userBl.finUserbiByUsername(s);}
}
