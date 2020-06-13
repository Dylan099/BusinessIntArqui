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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "graphic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Graphic.findAll", query = "SELECT g FROM Graphic g"),
    @NamedQuery(name = "Graphic.findByIdGraphic", query = "SELECT g FROM Graphic g WHERE g.idGraphic = :idGraphic"),
    @NamedQuery(name = "Graphic.findByName", query = "SELECT g FROM Graphic g WHERE g.name = :name"),
    @NamedQuery(name = "Graphic.findByDescription", query = "SELECT g FROM Graphic g WHERE g.description = :description"),
    @NamedQuery(name = "Graphic.findByTxHost", query = "SELECT g FROM Graphic g WHERE g.txHost = :txHost"),
    @NamedQuery(name = "Graphic.findByTxUser", query = "SELECT g FROM Graphic g WHERE g.txUser = :txUser"),
    @NamedQuery(name = "Graphic.findByTxDate", query = "SELECT g FROM Graphic g WHERE g.txDate = :txDate")})
public class Graphic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_graphic")
    private Integer idGraphic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGraphic", fetch = FetchType.LAZY)
    private Collection<GraphicDashboard> graphicDashboardCollection;
    @JoinColumn(name = "id_query", referencedColumnName = "id_query")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Query idQuery;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idUser;

    public Graphic() {
    }

    public Graphic(Integer idGraphic) {
        this.idGraphic = idGraphic;
    }

    public Graphic(Integer idGraphic, String name, String description, String txHost, String txUser, Date txDate) {
        this.idGraphic = idGraphic;
        this.name = name;
        this.description = description;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdGraphic() {
        return idGraphic;
    }

    public void setIdGraphic(Integer idGraphic) {
        this.idGraphic = idGraphic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Collection<GraphicDashboard> getGraphicDashboardCollection() {
        return graphicDashboardCollection;
    }

    public void setGraphicDashboardCollection(Collection<GraphicDashboard> graphicDashboardCollection) {
        this.graphicDashboardCollection = graphicDashboardCollection;
    }

    public Query getIdQuery() {
        return idQuery;
    }

    public void setIdQuery(Query idQuery) {
        this.idQuery = idQuery;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGraphic != null ? idGraphic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Graphic)) {
            return false;
        }
        Graphic other = (Graphic) object;
        if ((this.idGraphic == null && other.idGraphic != null) || (this.idGraphic != null && !this.idGraphic.equals(other.idGraphic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Graphic[ idGraphic=" + idGraphic + " ]";
    }
    
}
