package com.example.ProyectoBIArqui.dto;

import java.sql.Date;

public class DashboardDto {

    private int idDashboard;
    private String name;
    private String description;
    private String txHost;
    private String txUser;
    private Date txDate;

    public DashboardDto(int idDashboard, String name, String description, String txHost, String txUser, Date txDate) {
        this.idDashboard = idDashboard;
        this.name = name;
        this.description = description;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public DashboardDto(int idDashboard, String name, String description) {
        this.idDashboard = idDashboard;
        this.name = name;
        this.description = description;
    }

    public int getIdDashboard() {
        return idDashboard;
    }

    public void setIdDashboard(int idDashboard) {
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

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "DashboardDto{" +
                "idDashboard=" + idDashboard +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                '}';
    }
}

