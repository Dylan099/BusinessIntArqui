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
@Table(name = "graphic_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GraphicType.findAll", query = "SELECT g FROM GraphicType g"),
    @NamedQuery(name = "GraphicType.findByIdGraphicType", query = "SELECT g FROM GraphicType g WHERE g.idGraphicType = :idGraphicType"),
    @NamedQuery(name = "GraphicType.findByType", query = "SELECT g FROM GraphicType g WHERE g.type = :type"),
    @NamedQuery(name = "GraphicType.findByTxHost", query = "SELECT g FROM GraphicType g WHERE g.txHost = :txHost"),
    @NamedQuery(name = "GraphicType.findByTxUser", query = "SELECT g FROM GraphicType g WHERE g.txUser = :txUser"),
    @NamedQuery(name = "GraphicType.findByTxDate", query = "SELECT g FROM GraphicType g WHERE g.txDate = :txDate")})
public class GraphicType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_graphic_type")
    private Integer idGraphicType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGraphicType", fetch = FetchType.LAZY)
    private Collection<Graphic> graphicCollection;

    public GraphicType() {
    }

    public GraphicType(Integer idGraphicType) {
        this.idGraphicType = idGraphicType;
    }

    public GraphicType(Integer idGraphicType, String type, String txHost, String txUser, Date txDate) {
        this.idGraphicType = idGraphicType;
        this.type = type;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdGraphicType() {
        return idGraphicType;
    }

    public void setIdGraphicType(Integer idGraphicType) {
        this.idGraphicType = idGraphicType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (idGraphicType != null ? idGraphicType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GraphicType)) {
            return false;
        }
        GraphicType other = (GraphicType) object;
        if ((this.idGraphicType == null && other.idGraphicType != null) || (this.idGraphicType != null && !this.idGraphicType.equals(other.idGraphicType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.GraphicType[ idGraphicType=" + idGraphicType + " ]";
    }
    
}
