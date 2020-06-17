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
@Table(name = "userbi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userbi.findAll", query = "SELECT u FROM Userbi u"),
    @NamedQuery(name = "Userbi.findByIdUserbi", query = "SELECT u FROM Userbi u WHERE u.idUserbi = :idUserbi"),
    @NamedQuery(name = "Userbi.findByName", query = "SELECT u FROM Userbi u WHERE u.name = :name"),
    @NamedQuery(name = "Userbi.findByLastname", query = "SELECT u FROM Userbi u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Userbi.findByUsername", query = "SELECT u FROM Userbi u WHERE u.username = :username"),
    @NamedQuery(name = "Userbi.findByPassword", query = "SELECT u FROM Userbi u WHERE u.password = :password"),
    @NamedQuery(name = "Userbi.findByTxHost", query = "SELECT u FROM Userbi u WHERE u.txHost = :txHost"),
    @NamedQuery(name = "Userbi.findByTxUser", query = "SELECT u FROM Userbi u WHERE u.txUser = :txUser"),
    @NamedQuery(name = "Userbi.findByTxDate", query = "SELECT u FROM Userbi u WHERE u.txDate = :txDate")})
public class Userbi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_userbi")
    private Integer idUserbi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserbi", fetch = FetchType.LAZY)
    private Collection<Graphic> graphicCollection;

    public Userbi() {
    }

    public Userbi(Integer idUserbi) {
        this.idUserbi = idUserbi;
    }

    public Userbi(Integer idUserbi, String name, String lastname, String username, String password, String txHost, String txUser, Date txDate) {
        this.idUserbi = idUserbi;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getIdUserbi() {
        return idUserbi;
    }

    public void setIdUserbi(Integer idUserbi) {
        this.idUserbi = idUserbi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        hash += (idUserbi != null ? idUserbi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userbi)) {
            return false;
        }
        Userbi other = (Userbi) object;
        if ((this.idUserbi == null && other.idUserbi != null) || (this.idUserbi != null && !this.idUserbi.equals(other.idUserbi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Userbi[ idUserbi=" + idUserbi + " ]";
    }
    
}
