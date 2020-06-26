package com.example.ProyectoBIArqui.security;

import com.example.ProyectoBIArqui.dao.UserRepository;
import com.example.ProyectoBIArqui.domain.Userbi;
import com.example.ProyectoBIArqui.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Userbi userbi = userRepository.findUserbiByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(userbi);
        return userPrincipal;
    }
}
