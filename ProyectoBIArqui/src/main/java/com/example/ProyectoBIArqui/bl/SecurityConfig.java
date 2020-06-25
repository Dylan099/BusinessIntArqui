package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.UserRepository;
import com.example.ProyectoBIArqui.domain.Userbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private UserDetailsService userDetailsService;
    private UserRepository userRepository;

    public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //auth.inMemoryAuthentication().withUser("user").password("{noop}test").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/*")
                .access("hasRole('ROLE_USER')");
        http.formLogin()
                .defaultSuccessUrl("/", true);
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/login");

    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
