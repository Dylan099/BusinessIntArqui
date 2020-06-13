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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmari
 */
@Entity
@Table(name = "graphicvar_graphictype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GraphicvarGraphictype.findAll", query = "SELECT g FROM GraphicvarGraphictype g"),
    @NamedQuery(name = "GraphicvarGraphictype.findByIdGvt", query = "SELECT g FROM GraphicvarGraphictype g WHERE g.idGvt = :idGvt"),
    @NamedQuery(name = "GraphicvarGraphictype.findByTxHost", query = "SELECT g FROM GraphicvarGraphictype g WHERE g.txHost = :txHost"),
    @NamedQuery(name = "GraphicvarGraphictype.findByTxUser", query = "SELECT g FROM GraphicvarGraphictype g WHERE g.txUser = :txUser"),
    @NamedQuery(name = "GraphicvarGraphictype.findByTxDate", query = "SELECT g FROM GraphicvarGraphictype g WHERE g.txDate = :txDate")})
public class GraphicvarGraphictype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_gvt")
    private Integer idGvt;
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
    @JoinColumn(name = "graphic_type_id_graphic_type", referencedColumnName = "id_graphic_type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GraphicType graphicTypeIdGraphicType;
    @JoinColumn(name = "graphic_variable_id_graphic_variable", referencedColumnName = "id_graphic_variable")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GraphicVariable graphicVariableIdGraphicVariable;

    public GraphicvarGraphictype() {
    }

    public GraphicvarGraphictype(Integer idGvt) {
        this.idGvt = idGvt;
    }

    public GraphicvarGraphictype(Integer idGvt, String txHost, String txUser, Date txDate) {
        this.idGvt = idGvt;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdGvt() {
        return idGvt;
    }

    public void setIdGvt(Integer idGvt) {
        this.idGvt = idGvt;
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

    public GraphicType getGraphicTypeIdGraphicType() {
        return graphicTypeIdGraphicType;
    }

    public void setGraphicTypeIdGraphicType(GraphicType graphicTypeIdGraphicType) {
        this.graphicTypeIdGraphicType = graphicTypeIdGraphicType;
    }

    public GraphicVariable getGraphicVariableIdGraphicVariable() {
        return graphicVariableIdGraphicVariable;
    }

    public void setGraphicVariableIdGraphicVariable(GraphicVariable graphicVariableIdGraphicVariable) {
        this.graphicVariableIdGraphicVariable = graphicVariableIdGraphicVariable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGvt != null ? idGvt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GraphicvarGraphictype)) {
            return false;
        }
        GraphicvarGraphictype other = (GraphicvarGraphictype) object;
        if ((this.idGvt == null && other.idGvt != null) || (this.idGvt != null && !this.idGvt.equals(other.idGvt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.GraphicvarGraphictype[ idGvt=" + idGvt + " ]";
    }
    
}
