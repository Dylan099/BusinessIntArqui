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
import javax.persistence.Id;
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
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdReport", query = "SELECT r FROM Report r WHERE r.idReport = :idReport"),
    @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date"),
    @NamedQuery(name = "Report.findByTxHost", query = "SELECT r FROM Report r WHERE r.txHost = :txHost"),
    @NamedQuery(name = "Report.findByTxUser", query = "SELECT r FROM Report r WHERE r.txUser = :txUser"),
    @NamedQuery(name = "Report.findByTxDate", query = "SELECT r FROM Report r WHERE r.txDate = :txDate"),
    @NamedQuery(name = "Report.findByIdDashboard", query = "SELECT r FROM Report r WHERE r.idDashboard = :idDashboard")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_report")
    private Integer idReport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
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
    @Column(name = "id_dashboard")
    private int idDashboard;

    public Report() {
    }

    public Report(Integer idReport) {
        this.idReport = idReport;
    }

    public Report(Integer idReport, Date date, String txHost, String txUser, Date txDate, int idDashboard) {
        this.idReport = idReport;
        this.date = date;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.idDashboard = idDashboard;
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getIdDashboard() {
        return idDashboard;
    }

    public void setIdDashboard(int idDashboard) {
        this.idDashboard = idDashboard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReport != null ? idReport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idReport == null && other.idReport != null) || (this.idReport != null && !this.idReport.equals(other.idReport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProyectoBIArqui.domain.Report[ idReport=" + idReport + " ]";
    }
    
}
