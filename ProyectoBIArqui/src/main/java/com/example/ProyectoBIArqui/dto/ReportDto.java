package com.example.vaadinViews.dto;

import java.sql.Date;

public class ReportDto {
    private int idReport;
    private Date date;
    private int idDashboard;
    private String txHost;
    private String txUser;
    private Date txDate;

    public ReportDto(int idReport, Date date, int idDashboard, String txHost, String txUser, Date txDate) {
        this.idReport = idReport;
        this.date = date;
        this.idDashboard = idDashboard;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public ReportDto(int idReport, Date date, int idDashboard) {
        this.idReport = idReport;
        this.date = date;
        this.idDashboard = idDashboard;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdDashboard() {
        return idDashboard;
    }

    public void setIdDashboard(int idDashboard) {
        this.idDashboard = idDashboard;
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

    @Override
    public String toString() {
        return "ReportDto{" +
                "idReport=" + idReport +
                ", date=" + date +
                ", idDashboard=" + idDashboard +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                '}';
    }
}

