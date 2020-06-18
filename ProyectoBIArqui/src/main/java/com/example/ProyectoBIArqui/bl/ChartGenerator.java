package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicTypeController;
import com.example.ProyectoBIArqui.api.GraphicVariableController;
import com.example.ProyectoBIArqui.api.QueryController;
import com.example.ProyectoBIArqui.dao.EstadoRepository;
import com.example.ProyectoBIArqui.dao.PersonaRepository;
import com.example.ProyectoBIArqui.dao.ResidenciaRepository;
import com.example.ProyectoBIArqui.domain.*;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartGenerator {

    private Graphic graphic;
    private GraphicController graphicController;
    private QueryController queryController;
    private GraphicTypeController graphicTypeController;
    private GraphicVariableController graphicVariableController;
    private EstadoRepository estadoRepository;
    private ResidenciaRepository residenciaRepository;
    private PersonaRepository personaRepository;

    @Autowired
    public ChartGenerator(GraphicController graphicController, QueryController queryController, GraphicTypeController graphicTypeController, GraphicVariableController graphicVariableController, EstadoRepository estadoRepository, ResidenciaRepository residenciaRepository, PersonaRepository personaRepository) {
        this.graphicController = graphicController;
        this.queryController = queryController;
        this.graphicTypeController = graphicTypeController;
        this.graphicVariableController = graphicVariableController;
        this.estadoRepository = estadoRepository;
        this.residenciaRepository = residenciaRepository;
        this.personaRepository = personaRepository;
    }

    public Graphic getGraphic() {
        return graphic;
    }

    public void setGraphic(Graphic graphic) {
        this.graphic = graphic;
    }

    public Chart GenerarGrafica() {
        Chart chart = new Chart(ChartType.LINE);
        final Configuration configuration = chart.getConfiguration();

        configuration.setTitle(graphic.getName());
        configuration.setSubTitle(graphic.getDescription());

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

        configuration.getTooltip().setValueSuffix(" personas");

        PlotOptionsArea plotOptionsArea = new PlotOptionsArea();
        plotOptionsArea.setStacking(Stacking.NORMAL);
        configuration.setPlotOptions(plotOptionsArea);
        configuration.addSeries(n);

        return chart;
    }

}
