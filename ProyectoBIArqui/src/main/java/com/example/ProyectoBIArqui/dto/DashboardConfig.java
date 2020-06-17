package com.example.ProyectoBIArqui.dto;

import com.example.ProyectoBIArqui.domain.Graphic;

import java.util.List;

public class DashboardConfig {

    String name;
    String desc;
    List<Graphic> graphicList;

    public DashboardConfig() {
    }

    public DashboardConfig(String name, String desc, List<Graphic> graphicList) {
        this.name = name;
        this.desc = desc;
        this.graphicList = graphicList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Graphic> getGraphicList() {
        return graphicList;
    }

    public void setGraphicList(List<Graphic> graphicList) {
        this.graphicList = graphicList;
    }
}
