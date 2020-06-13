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
@Table(name = "query")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Query.findAll", query = "SELECT q FROM Query q"),
    @NamedQuery(name = "Query.findByIdQuery", query = "SELECT q FROM Query q WHERE q.idQuery = :idQuery"),
    @NamedQuery(name = "Query.findByQuery", query = "SELECT q FROM Query q WHERE q.query = :query"),
    @NamedQuery(name = "Query.findByTxHost", query = "SELECT q FROM Query q WHERE q.txHost = :txHost"),
    @NamedQuery(name = "Query.findByTxUser", query = "SELECT q FROM Query q WHERE q.txUser = :txUser"),
    @NamedQuery(name = "Query.findByTxDate", query = "SELECT q FROM Query q WHERE q.txDate = :txDate")})
public class Query implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_query")
    private Integer idQuery;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuery", fetch = FetchType.LAZY)
    private Collection<QueryGraphicType> queryGraphicTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuery", fetch = FetchType.LAZY)
    private Collection<Graphic> graphicCollection;

    public Query() {
    }

    public Query(Integer idQuery) {
        this.idQuery = idQuery;
    }

    public Query(Integer idQuery, String query, String txHost, String txUser, Date txDate) {
        this.idQuery = idQuery;
        this.query = query;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdQuery() {
        return idQuery;
    }

    public void setIdQuery(Integer idQuery) {
        this.idQuery = idQuery;
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
    public Collection<QueryGraphicType> getQueryGraphicTypeCollection() {
        return queryGraphicTypeCollection;
    }

    public void setQueryGraphicTypeCollection(Collection<QueryGraphicType> queryGraphicTypeCollection) {
        this.queryGraphicTypeCollection = queryGraphicTypeCollection;
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
        hash += (idQuery != null ? idQuery.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Query)) {
            return false;
        }
        Query other = (Query) object;
        if ((this.idQuery == null && other.idQuery != null) || (this.idQuery != null && !this.idQuery.equals(other.idQuery))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Query[ idQuery=" + idQuery + " ]";
    }
    
}
