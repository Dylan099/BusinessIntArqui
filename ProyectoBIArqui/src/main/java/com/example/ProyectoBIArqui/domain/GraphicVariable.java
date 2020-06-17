/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProyectoBIArqui.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmari
 */
@Entity
@Table(name = "graphic_variable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GraphicVariable.findAll", query = "SELECT g FROM GraphicVariable g"),
    @NamedQuery(name = "GraphicVariable.findByIdGraphicVariable", query = "SELECT g FROM GraphicVariable g WHERE g.idGraphicVariable = :idGraphicVariable"),
    @NamedQuery(name = "GraphicVariable.findByTxHost", query = "SELECT g FROM GraphicVariable g WHERE g.txHost = :txHost"),
    @NamedQuery(name = "GraphicVariable.findByTxUser", query = "SELECT g FROM GraphicVariable g WHERE g.txUser = :txUser"),
    @NamedQuery(name = "GraphicVariable.findByTxDate", query = "SELECT g FROM GraphicVariable g WHERE g.txDate = :txDate"),
    @NamedQuery(name = "GraphicVariable.findByVariable", query = "SELECT g FROM GraphicVariable g WHERE g.variable = :variable")})
public class GraphicVariable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_graphic_variable")
    private Integer idGraphicVariable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tx_user")
    private String txUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "variable")
    private String variable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGraphicVariable", fetch = FetchType.LAZY)
    private Collection<Graphic> graphicCollection;

    public GraphicVariable() {
    }

    public GraphicVariable(Integer idGraphicVariable) {
        this.idGraphicVariable = idGraphicVariable;
    }

    public GraphicVariable(Integer idGraphicVariable, String txHost, String txUser, Date txDate, String variable) {
        this.idGraphicVariable = idGraphicVariable;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.variable = variable;
    }

    public Integer getIdGraphicVariable() {
        return idGraphicVariable;
    }

    public void setIdGraphicVariable(Integer idGraphicVariable) {
        this.idGraphicVariable = idGraphicVariable;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @XmlTransient
    public Collection<Graphic> getGraphicCollection() {
        return graphicCollection;
    }

    public void setGraphicCollection(Collection<Graphic> graphicCollection) {
        this.graphicCollection = graphicCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGraphicVariable != null ? idGraphicVariable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GraphicVariable)) {
            return false;
        }
        GraphicVariable other = (GraphicVariable) object;
        if ((this.idGraphicVariable == null && other.idGraphicVariable != null) || (this.idGraphicVariable != null && !this.idGraphicVariable.equals(other.idGraphicVariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.GraphicVariable[ idGraphicVariable=" + idGraphicVariable + " ]";
    }
    
}
