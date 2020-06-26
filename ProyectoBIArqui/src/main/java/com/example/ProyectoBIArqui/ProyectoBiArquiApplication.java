package com.example.ProyectoBIArqui;

import com.example.ProyectoBIArqui.dao.UserRepository;
import com.example.ProyectoBIArqui.domain.Userbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ProyectoBiArquiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBiArquiApplication.class, args);
	}

}
