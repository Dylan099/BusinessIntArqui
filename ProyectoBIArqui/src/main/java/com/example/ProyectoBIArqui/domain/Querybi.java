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
@Table(name = "querybi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Querybi.findAll", query = "SELECT q FROM Querybi q"),
    @NamedQuery(name = "Querybi.findByIdQuerybi", query = "SELECT q FROM Querybi q WHERE q.idQuerybi = :idQuerybi"),
    @NamedQuery(name = "Querybi.findByQuery", query = "SELECT q FROM Querybi q WHERE q.query = :query"),
    @NamedQuery(name = "Querybi.findByTxHost", query = "SELECT q FROM Querybi q WHERE q.txHost = :txHost"),
    @NamedQuery(name = "Querybi.findByTxUser", query = "SELECT q FROM Querybi q WHERE q.txUser = :txUser"),
    @NamedQuery(name = "Querybi.findByTxDate", query = "SELECT q FROM Querybi q WHERE q.txDate = :txDate")})
public class Querybi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_querybi")
    private Integer idQuerybi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "query")
    private String query;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuerybi", fetch = FetchType.LAZY)
    private Collection<Graphic> graphicCollection;

    public Querybi() {
    }

    public Querybi(Integer idQuerybi) {
        this.idQuerybi = idQuerybi;
    }

    public Querybi(Integer idQuerybi, String query, String txHost, String txUser, Date txDate) {
        this.idQuerybi = idQuerybi;
        this.query = query;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdQuerybi() {
        return idQuerybi;
    }

    public void setIdQuerybi(Integer idQuerybi) {
        this.idQuerybi = idQuerybi;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
        hash += (idQuerybi != null ? idQuerybi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Querybi)) {
            return false;
        }
        Querybi other = (Querybi) object;
        if ((this.idQuerybi == null && other.idQuerybi != null) || (this.idQuerybi != null && !this.idQuerybi.equals(other.idQuerybi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Querybi[ idQuerybi=" + idQuerybi + " ]";
    }
    
}
