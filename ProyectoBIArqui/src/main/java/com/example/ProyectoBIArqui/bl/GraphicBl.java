package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.dao.*;
import com.example.ProyectoBIArqui.domain.Estado;
import com.example.ProyectoBIArqui.domain.Persona1;
import com.example.ProyectoBIArqui.domain.Residencia1;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.example.ProyectoBIArqui.dto.PersonaDto;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphicBl {

    GraphicRepository graphicRepository;
    PersonaRepository personaRepository;
    EstadoRepository estadoRepository;
    ResidenciaRepository residenciaRepository;
    DepartamentoRepository departamentoRepository;

    @Autowired
    public GraphicBl(GraphicRepository graphicRepository, PersonaRepository personaRepository, EstadoRepository estadoRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository) {
        this.graphicRepository = graphicRepository;
        this.personaRepository = personaRepository;
        this.estadoRepository = estadoRepository;
        this.residenciaRepository = residenciaRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public Chart generateGraphic(GraphicConfig graphicConfig)
    {
        Chart chart = new Chart(ChartType.LINE);
        final Configuration configuration = chart.getConfiguration();

        configuration.setTitle(graphicConfig.getTitulo());
        configuration.setSubTitle(graphicConfig.getDesc());

        Estado estado = estadoRepository.findEstadoByIdEstado(1);
        Residencia1 residencia1 = residenciaRepository.findResidencia1ByIdResidencia(1);

        List<Persona1> querySelected = personaRepository.findPersona1ByIdEstadoAndIdResidencia(estado,residencia1);
        int acum = 0, index = 0;
        String d = querySelected.get(0).getFecha().toString();
        XAxis xAxis = configuration.getxAxis();
        xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
        ListSeries n = new ListSeries();
        n.setName("La Paz");
        xAxis.addCategory(querySelected.get(0).getFecha().toString());
        n.addData(0);
        System.out.println("WASO CAAAAAAAAASCO ------->" + querySelected.size());
        for (Persona1 p:querySelected
             ) {
            acum++;
            if(!d.equals(p.getFecha().toString())){
                index++;
                d = p.getFecha().toString();
                xAxis.addCategory(d);
                n.addData(acum);
            }
        }

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("Contagios");
        yAxis.getLabels().setFormatter("function () { return this.value / 1000;}");

        configuration.getTooltip().setValueSuffix(" millions");

        PlotOptionsArea plotOptionsArea = new PlotOptionsArea();
        plotOptionsArea.setStacking(Stacking.NORMAL);
        configuration.setPlotOptions(plotOptionsArea);
        configuration.addSeries(n);

        return chart;
    }
}
