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
@Table(name = "graphic_dashboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GraphicDashboard.findAll", query = "SELECT g FROM GraphicDashboard g"),
    @NamedQuery(name = "GraphicDashboard.findByIdGraphicDashboard", query = "SELECT g FROM GraphicDashboard g WHERE g.idGraphicDashboard = :idGraphicDashboard"),
    @NamedQuery(name = "GraphicDashboard.findByTxHost", query = "SELECT g FROM GraphicDashboard g WHERE g.txHost = :txHost"),
    @NamedQuery(name = "GraphicDashboard.findByTxUser", query = "SELECT g FROM GraphicDashboard g WHERE g.txUser = :txUser"),
    @NamedQuery(name = "GraphicDashboard.findByTxDate", query = "SELECT g FROM GraphicDashboard g WHERE g.txDate = :txDate")})
public class GraphicDashboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_graphic_dashboard")
    private Integer idGraphicDashboard;
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
    @JoinColumn(name = "id_dashboard", referencedColumnName = "id_dashboard")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dashboard idDashboard;
    @JoinColumn(name = "id_graphic", referencedColumnName = "id_graphic")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Graphic idGraphic;

    public GraphicDashboard() {
    }

    public GraphicDashboard(Integer idGraphicDashboard) {
        this.idGraphicDashboard = idGraphicDashboard;
    }

    public GraphicDashboard(Integer idGraphicDashboard, String txHost, String txUser, Date txDate) {
        this.idGraphicDashboard = idGraphicDashboard;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdGraphicDashboard() {
        return idGraphicDashboard;
    }

    public void setIdGraphicDashboard(Integer idGraphicDashboard) {
        this.idGraphicDashboard = idGraphicDashboard;
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

    public Dashboard getIdDashboard() {
        return idDashboard;
    }

    public void setIdDashboard(Dashboard idDashboard) {
        this.idDashboard = idDashboard;
    }

    public Graphic getIdGraphic() {
        return idGraphic;
    }

    public void setIdGraphic(Graphic idGraphic) {
        this.idGraphic = idGraphic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGraphicDashboard != null ? idGraphicDashboard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GraphicDashboard)) {
            return false;
        }
        GraphicDashboard other = (GraphicDashboard) object;
        if ((this.idGraphicDashboard == null && other.idGraphicDashboard != null) || (this.idGraphicDashboard != null && !this.idGraphicDashboard.equals(other.idGraphicDashboard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.GraphicDashboard[ idGraphicDashboard=" + idGraphicDashboard + " ]";
    }
    
}
