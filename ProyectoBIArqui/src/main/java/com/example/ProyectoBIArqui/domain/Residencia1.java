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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmari
 */
@Entity
@Table(name = "residencia_1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Residencia1.findAll", query = "SELECT r FROM Residencia1 r"),
    @NamedQuery(name = "Residencia1.findByIdResidencia", query = "SELECT r FROM Residencia1 r WHERE r.idResidencia = :idResidencia"),
    @NamedQuery(name = "Residencia1.findByMunicipio", query = "SELECT r FROM Residencia1 r WHERE r.municipio = :municipio"),
    @NamedQuery(name = "Residencia1.findByZona", query = "SELECT r FROM Residencia1 r WHERE r.zona = :zona")})
public class Residencia1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_residencia")
    private Integer idResidencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 50)
    @Column(name = "zona")
    private String zona;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamento idDepartamento;
    @OneToMany(mappedBy = "idResidencia", fetch = FetchType.LAZY)
    private Collection<Persona1> persona1Collection;

    public Residencia1() {
    }

    public Residencia1(Integer idResidencia) {
        this.idResidencia = idResidencia;
    }

    public Residencia1(Integer idResidencia, String municipio) {
        this.idResidencia = idResidencia;
        this.municipio = municipio;
    }

    public Integer getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(Integer idResidencia) {
        this.idResidencia = idResidencia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
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
        hash += (idResidencia != null ? idResidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Residencia1)) {
            return false;
        }
        Residencia1 other = (Residencia1) object;
        if ((this.idResidencia == null && other.idResidencia != null) || (this.idResidencia != null && !this.idResidencia.equals(other.idResidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Residencia1[ idResidencia=" + idResidencia + " ]";
    }
    
}
