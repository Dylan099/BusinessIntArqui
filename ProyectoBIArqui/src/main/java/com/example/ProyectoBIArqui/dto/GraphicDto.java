package com.example.vaadinViews.dto;

import java.sql.Date;

public class GraphicDto {
    private int idGraphic;
    private String name;
    private String query;
    private String type;
    private String description;
    private int idUser;
    private String txHost;
    private String txUser;
    private Date txDate;

    public GraphicDto(int idGraphic, String name, String query, String type, String description, int idUser, String txHost, String txUser, Date txDate) {
        this.idGraphic = idGraphic;
        this.name = name;
        this.query = query;
        this.type = type;
        this.description = description;
        this.idUser = idUser;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public GraphicDto(int idGraphic, String name, String query, String type, String description) {
        this.idGraphic = idGraphic;
        this.name = name;
        this.query = query;
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdGraphic() {
        return idGraphic;
    }

    public void setIdGraphic(int idGraphic) {
        this.idGraphic = idGraphic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        return "GraphicDto{" +
                "idGraphic=" + idGraphic +
                ", name='" + name + '\'' +
                ", query='" + query + '\'' +
                ", description='" + description + '\'' +
                ", idUser=" + idUser +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                '}';
    }
}
