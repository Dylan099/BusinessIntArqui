/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProyectoBIArqui.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmari
 */
@Entity
@Table(name = "edad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edad.findAll", query = "SELECT e FROM Edad e"),
    @NamedQuery(name = "Edad.findByIdEdad", query = "SELECT e FROM Edad e WHERE e.idEdad = :idEdad"),
    @NamedQuery(name = "Edad.findByEdad", query = "SELECT e FROM Edad e WHERE e.edad = :edad")})
public class Edad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_edad")
    private Integer idEdad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private int edad;
    @OneToMany(mappedBy = "idEdad", fetch = FetchType.LAZY)
    private Collection<Persona1> persona1Collection;

    public Edad() {
    }

    public Edad(Integer idEdad) {
        this.idEdad = idEdad;
    }

    public Edad(Integer idEdad, int edad) {
        this.idEdad = idEdad;
        this.edad = edad;
    }

    public Integer getIdEdad() {
        return idEdad;
    }

    public void setIdEdad(Integer idEdad) {
        this.idEdad = idEdad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlTransient
    public Collection<Persona1> getPersona1Collection() {
        return persona1Collection;
    }

    public void setPersona1Collection(Collection<Persona1> persona1Collection) {
        this.persona1Collection = persona1Collection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEdad != null ? idEdad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edad)) {
            return false;
        }
        Edad other = (Edad) object;
        if ((this.idEdad == null && other.idEdad != null) || (this.idEdad != null && !this.idEdad.equals(other.idEdad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Edad[ idEdad=" + idEdad + " ]";
    }
    
}
