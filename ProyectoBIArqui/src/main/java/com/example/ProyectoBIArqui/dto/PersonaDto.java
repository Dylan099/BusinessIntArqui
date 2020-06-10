package com.example.ProyectoBIArqui.dto;

import com.example.ProyectoBIArqui.domain.Persona1;

public class PersonaDto {
    private Integer idpersona;
    private String fecha;
    private String edad;
    private String estado;
    private String sexo;
    private String residencia;
    private String origen;

    public PersonaDto(){
    }

    public PersonaDto(Persona1 persona1) {
        this.idpersona = persona1.getIdPersona();
        this.fecha = persona1.getFecha().toString();
        this.edad = persona1.getIdEdad().toString();
        this.estado = persona1.getIdEstado().toString();
        this.sexo = persona1.getIdSexo().toString();
        this.residencia = persona1.getIdResidencia().toString();
        this.origen = persona1.getIdOrigen().toString();
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "PersonaDto{" +
                "idpersona=" + idpersona +
                ", fecha='" + fecha + '\'' +
                ", edad='" + edad + '\'' +
                ", estado='" + estado + '\'' +
                ", sexo='" + sexo + '\'' +
                ", residencia='" + residencia + '\'' +
                ", origen='" + origen + '\'' +
                '}';
    }
}
