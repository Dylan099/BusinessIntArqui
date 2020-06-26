package com.example.ProyectoBIArqui.view;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

import java.util.Collections;

@Route("login")
@Theme(value = Lumo.class,variant = Lumo.DARK)
@PageTitle("Login Sistema de Bussiness Inteligence Covid-19 Bolivia")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    LoginForm login = new LoginForm();
    public LoginView(){

        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);

        Icon icon = VaadinIcon.AMBULANCE.create();
        icon.setSize("50px");
        icon.getStyle().set("top", "-4px");
        H1 title = new H1();
        title.add(icon);
        title.add(new Text("Covid-19 Bolivia"));

        add(title);
        add(login);
        Button registration = new Button("Registration");
        registration.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Ir a registro"));
            add(message);
        });

        LoginForm component = new LoginForm();
        component.setForgotPasswordButtonVisible(false);

        component.addLoginListener(e -> {
            boolean isAuthenticated = false;//authenticate(e);
            if (isAuthenticated) {
                H2 message = new H2();
                message.add(new Text("Ir a graphics"));
                add(message);
            } else {
                component.setError(true);
                component.setEnabled(false); // aca deberia ser despues de 3 intentos o algo asi
            }
        });

        setHorizontalComponentAlignment(Alignment.END, registration);
        setHorizontalComponentAlignment(Alignment.CENTER,title);
        setHorizontalComponentAlignment(Alignment.CENTER,component);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(!beforeEnterEvent.getLocation()
        .getQueryParameters().getParameters().getOrDefault("error", Collections.emptyList())
        .isEmpty()){
            login.setError(true);
        }
    }
}
