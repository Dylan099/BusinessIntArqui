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
@Table(name = "query_graphic_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QueryGraphicType.findAll", query = "SELECT q FROM QueryGraphicType q"),
    @NamedQuery(name = "QueryGraphicType.findByIdQgt", query = "SELECT q FROM QueryGraphicType q WHERE q.idQgt = :idQgt"),
    @NamedQuery(name = "QueryGraphicType.findByTxHost", query = "SELECT q FROM QueryGraphicType q WHERE q.txHost = :txHost"),
    @NamedQuery(name = "QueryGraphicType.findByTxUser", query = "SELECT q FROM QueryGraphicType q WHERE q.txUser = :txUser"),
    @NamedQuery(name = "QueryGraphicType.findByTxDate", query = "SELECT q FROM QueryGraphicType q WHERE q.txDate = :txDate")})
public class QueryGraphicType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_qgt")
    private Integer idQgt;
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
    @JoinColumn(name = "id_graphic_type", referencedColumnName = "id_graphic_type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GraphicType idGraphicType;
    @JoinColumn(name = "id_query", referencedColumnName = "id_query")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Query idQuery;

    public QueryGraphicType() {
    }

    public QueryGraphicType(Integer idQgt) {
        this.idQgt = idQgt;
    }

    public QueryGraphicType(Integer idQgt, String txHost, String txUser, Date txDate) {
        this.idQgt = idQgt;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdQgt() {
        return idQgt;
    }

    public void setIdQgt(Integer idQgt) {
        this.idQgt = idQgt;
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

    public GraphicType getIdGraphicType() {
        return idGraphicType;
    }

    public void setIdGraphicType(GraphicType idGraphicType) {
        this.idGraphicType = idGraphicType;
    }

    public Query getIdQuery() {
        return idQuery;
    }

    public void setIdQuery(Query idQuery) {
        this.idQuery = idQuery;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQgt != null ? idQgt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QueryGraphicType)) {
            return false;
        }
        QueryGraphicType other = (QueryGraphicType) object;
        if ((this.idQgt == null && other.idQgt != null) || (this.idQgt != null && !this.idQgt.equals(other.idQgt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.QueryGraphicType[ idQgt=" + idQgt + " ]";
    }
    
}
