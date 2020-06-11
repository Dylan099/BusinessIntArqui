package com.example.vaadinViews.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Route("registration")
@Theme(value = Material.class)
public class RegistrationView extends VerticalLayout {

    public RegistrationView(){

        H1 title = new H1();
        title.add(new Text("Registro para: Covid-19 Bolivia"));

        FormLayout columnLayout = new FormLayout();
        columnLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("50em", 1),
                new FormLayout.ResponsiveStep("50em", 2));

        TextField firstName = new TextField();
        firstName.setPlaceholder("Nombre");
        TextField lastName = new TextField();
        lastName.setPlaceholder("Apellido");
        TextField nickname = new TextField();
        nickname.setPlaceholder("Apodo");

        PasswordField password1 = new PasswordField();
        password1.setLabel("Password");
        password1.setPlaceholder("Enter password");

        PasswordField password2 = new PasswordField();
        password2.setLabel("Password");
        password2.setPlaceholder("Enter password");


        Button registration = new Button("Registrarse");
        registration.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Registro hecho, ir a graphics"));
            add(message);
        });
        Button back = new Button("Atras");
        back.addClickListener(e -> {
            H2 message = new H2();
            message.add(new Text("Volver a login"));
            add(message);
        });

        columnLayout.add(firstName, lastName,  nickname, password1, password2,registration,back);
        columnLayout.setColspan(nickname, 2);




        setHorizontalComponentAlignment(Alignment.CENTER, title);
        setHorizontalComponentAlignment(Alignment.CENTER, columnLayout);

        add(title);
        add(columnLayout);
    }

}
