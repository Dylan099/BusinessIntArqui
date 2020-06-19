package com.example.ProyectoBIArqui.dto;

import com.example.ProyectoBIArqui.domain.Graphic;

public class GraphicConfig {
    String titulo;
    String query;
    String graphicType;
    String varInd;
    String desc;

    public GraphicConfig() {
    }

    public GraphicConfig(String titulo, String query, String graphicType, String varInd, String desc) {
        this.titulo = titulo;
        this.query = query;
        this.graphicType = graphicType;
        this.varInd = varInd;
        this.desc = desc;
    }

    public GraphicConfig(Graphic graphic){
        this.titulo = graphic.getName();
        this.query = "Mostrar numero de contagios en La Paz";
        this.graphicType = "Grafico de lineas";
        this.varInd = "Fecha";
        this.desc = graphic.getDescription();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getGraphicType() {
        return graphicType;
    }

    public void setGraphicType(String graphicType) {
        this.graphicType = graphicType;
    }

    public String getVarInd() {
        return varInd;
    }

    public void setVarInd(String varInd) {
        this.varInd = varInd;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
