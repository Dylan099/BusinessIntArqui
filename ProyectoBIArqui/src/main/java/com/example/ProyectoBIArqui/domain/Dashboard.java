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
@Table(name = "dashboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dashboard.findAll", query = "SELECT d FROM Dashboard d"),
    @NamedQuery(name = "Dashboard.findByIdDashboard", query = "SELECT d FROM Dashboard d WHERE d.idDashboard = :idDashboard"),
    @NamedQuery(name = "Dashboard.findByName", query = "SELECT d FROM Dashboard d WHERE d.name = :name"),
    @NamedQuery(name = "Dashboard.findByDescription", query = "SELECT d FROM Dashboard d WHERE d.description = :description"),
    @NamedQuery(name = "Dashboard.findByTxHost", query = "SELECT d FROM Dashboard d WHERE d.txHost = :txHost"),
    @NamedQuery(name = "Dashboard.findByTxUser", query = "SELECT d FROM Dashboard d WHERE d.txUser = :txUser"),
    @NamedQuery(name = "Dashboard.findByTDate", query = "SELECT d FROM Dashboard d WHERE d.tDate = :tDate")})
public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_dashboard")
    private Integer idDashboard;
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
    @Column(name = "t_date")
    @Temporal(TemporalType.DATE)
    private Date tDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDashboard", fetch = FetchType.LAZY)
    private Collection<GraphicDashboard> graphicDashboardCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDashboard", fetch = FetchType.LAZY)
    private Collection<Report> reportCollection;

    public Dashboard() {
    }

    public Dashboard(Integer idDashboard) {
        this.idDashboard = idDashboard;
    }

    public Dashboard(Integer idDashboard, String name, String description, String txHost, String txUser, Date tDate) {
        this.idDashboard = idDashboard;
        this.name = name;
        this.description = description;
        this.txHost = txHost;
        this.txUser = txUser;
        this.tDate = tDate;
    }

    public Integer getIdDashboard() {
        return idDashboard;
    }

    public void setIdDashboard(Integer idDashboard) {
        this.idDashboard = idDashboard;
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

    public Date getTDate() {
        return tDate;
    }

    public void setTDate(Date tDate) {
        this.tDate = tDate;
    }

    @XmlTransient
    public Collection<GraphicDashboard> getGraphicDashboardCollection() {
        return graphicDashboardCollection;
    }

    public void setGraphicDashboardCollection(Collection<GraphicDashboard> graphicDashboardCollection) {
        this.graphicDashboardCollection = graphicDashboardCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDashboard != null ? idDashboard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dashboard)) {
            return false;
        }
        Dashboard other = (Dashboard) object;
        if ((this.idDashboard == null && other.idDashboard != null) || (this.idDashboard != null && !this.idDashboard.equals(other.idDashboard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Dashboard[ idDashboard=" + idDashboard + " ]";
    }
    
}
