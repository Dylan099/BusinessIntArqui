/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProyectoBIArqui.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmari
 */
@Entity
@Table(name = "persona_1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona1.findAll", query = "SELECT p FROM Persona1 p"),
    @NamedQuery(name = "Persona1.findByIdPersona", query = "SELECT p FROM Persona1 p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona1.findByFecha", query = "SELECT p FROM Persona1 p WHERE p.fecha = :fecha")})
public class Persona1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_edad", referencedColumnName = "id_edad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Edad idEdad;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estado idEstado;
    @JoinColumn(name = "id_origen", referencedColumnName = "id_origen")
    @ManyToOne(fetch = FetchType.LAZY)
    private Origen idOrigen;
    @JoinColumn(name = "id_residencia", referencedColumnName = "id_residencia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Residencia1 idResidencia;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sexo idSexo;

    public Persona1() {
    }

    public Persona1(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Edad getIdEdad() {
        return idEdad;
    }

    public void setIdEdad(Edad idEdad) {
        this.idEdad = idEdad;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Origen getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Origen idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Residencia1 getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(Residencia1 idResidencia) {
        this.idResidencia = idResidencia;
    }

    public Sexo getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Sexo idSexo) {
        this.idSexo = idSexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona1)) {
            return false;
        }
        Persona1 other = (Persona1) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Persona1[ idPersona=" + idPersona + " ]";
    }
    
}
