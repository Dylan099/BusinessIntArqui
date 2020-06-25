package com.example.ProyectoBIArqui.bl;

import com.example.ProyectoBIArqui.api.GraphicController;
import com.example.ProyectoBIArqui.api.GraphicTypeController;
import com.example.ProyectoBIArqui.api.GraphicVariableController;
import com.example.ProyectoBIArqui.api.QueryController;
import com.example.ProyectoBIArqui.dao.EstadoRepository;
import com.example.ProyectoBIArqui.dao.PersonaRepository;
import com.example.ProyectoBIArqui.dao.QueryRepository;
import com.example.ProyectoBIArqui.dao.ResidenciaRepository;
import com.example.ProyectoBIArqui.domain.*;
import com.example.ProyectoBIArqui.dto.GraphicConfig;
import com.example.ProyectoBIArqui.dto.QueryXY;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private QueryRepository queryRepository;

    @Autowired
    public ChartGenerator(GraphicController graphicController, QueryController queryController, GraphicTypeController graphicTypeController, GraphicVariableController graphicVariableController, EstadoRepository estadoRepository, ResidenciaRepository residenciaRepository, PersonaRepository personaRepository,
                          QueryRepository queryRepository) {
        this.graphicController = graphicController;
        this.queryController = queryController;
        this.graphicTypeController = graphicTypeController;
        this.graphicVariableController = graphicVariableController;
        this.estadoRepository = estadoRepository;
        this.residenciaRepository = residenciaRepository;
        this.personaRepository = personaRepository;
        this.queryRepository = queryRepository;
    }

    public Graphic getGraphic() {
        return graphic;
    }

    public void setGraphic(Graphic graphic) {
        this.graphic = graphic;
    }

    public Chart GenerarGrafica() {
        Chart chart;
        final Configuration configuration;
        XAxis xAxis;
        YAxis yAxis;
        PlotOptionsArea plotOptionsArea;
        switch (graphic.getIdGraphicType().getIdGraphicType())
        {
            case 1:
                //Grafico de lineas
                chart = new Chart(ChartType.LINE);
                configuration = chart.getConfiguration();
                configuration.setTitle(graphic.getName());
                configuration.setSubTitle(graphic.getDescription());
                xAxis = configuration.getxAxis();
                xAxis.setTickmarkPlacement(TickmarkPlacement.ON);

                ListSeries n = new ListSeries();
                n.setName("Eje Y");
                List<String> s1 = null;
                List<Integer> s2 = null;
                if(graphic.getIdQuerybi().getIdQuerybi() == 1){
                    return graphicController.generarGrafica(new GraphicConfig(graphic));
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 2)
                {
                    s1 = queryRepository.numeroAcumuladoDeContagiadosPorFecha1();
                    s2 = queryRepository.numeroAcumuladoDeContagiadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 3)
                {
                    s1 = queryRepository.contagiadosPorPais1();
                    s2 = queryRepository.contagiadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 4)
                {
                    s1 = queryRepository.contagiadosPorEdad1();
                    s2 = queryRepository.contagiadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 5)
                {
                    s1 = queryRepository.contagiadosPorDepartamento1();
                    s2 = queryRepository.contagiadosPorDepartamento2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 6)
                {
                    s1 = queryRepository.contagiadosPorSexo1();
                    s2 = queryRepository.contagiadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 7)
                {
                    s1 = queryRepository.recuperadosPorFecha1();
                    s2 = queryRepository.recuperadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 8)
                {
                    s1 = queryRepository.recuperadosPorPais1();
                    s2 = queryRepository.recuperadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 9)
                {
                    s1 = queryRepository.recuperadosPorEdad1();
                    s2 = queryRepository.recuperadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 10)
                {
                    s1 = queryRepository.recuperadosPorSexo1();
                    s2 = queryRepository.recuperadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 11)
                {
                    s1 = queryRepository.muertosPorFecha1();
                    s2 = queryRepository.muertosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 14)
                {
                    s1 = queryRepository.muertosPorPais1();
                    s2 = queryRepository.muertosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 12)
                {
                    s1 = queryRepository.muertosPorEdad1();
                    s2 = queryRepository.muertosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 13)
                {
                    s1 = queryRepository.muertosPorSexo1();
                    s2 = queryRepository.muertosPorSexo2();
                }
                List<QueryXY> dataset = new ArrayList<>();
                assert s1 != null;
                for (int i = 0; i<s1.size(); i++
                ) {
                    dataset.add(new QueryXY(s1.get(i),s2.get(i)));
                }
                for (QueryXY xy:dataset
                ) {
                    System.out.println("X: "+xy.getX()+" Y: "+xy.getY());
                    xAxis.addCategory(xy.getX());
                    n.addData(xy.getY());
                }

                yAxis = configuration.getyAxis();
                yAxis.setTitle("Eje X");
                yAxis.getLabels().setFormatter("function () { return this.value;}");

                configuration.getTooltip().setValueSuffix(" personas");

                plotOptionsArea = new PlotOptionsArea();
                plotOptionsArea.setStacking(Stacking.NORMAL);
                plotOptionsArea.setColorIndex(581682);

                configuration.setPlotOptions(plotOptionsArea);
                configuration.addSeries(n);

                return chart;
            case 2:
                //Grafico de Columnas
                chart = new Chart(ChartType.COLUMN);
                configuration = chart.getConfiguration();
                configuration.setTitle(graphic.getName());
                configuration.setSubTitle(graphic.getDescription());
                xAxis = configuration.getxAxis();
                xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
                ListSeries nc = new ListSeries();
                nc.setName("Eje Y");
                List<String> s1c = null;
                List<Integer> s2c = null;
                if(graphic.getIdQuerybi().getIdQuerybi() == 1){
                    return graphicController.generarGrafica(new GraphicConfig(graphic));
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 2)
                {
                    s1c = queryRepository.numeroAcumuladoDeContagiadosPorFecha1();
                    s2c = queryRepository.numeroAcumuladoDeContagiadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 3)
                {
                    s1c = queryRepository.contagiadosPorPais1();
                    s2c = queryRepository.contagiadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 4)
                {
                    s1c = queryRepository.contagiadosPorEdad1();
                    s2c = queryRepository.contagiadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 5)
                {
                    s1c = queryRepository.contagiadosPorDepartamento1();
                    s2c = queryRepository.contagiadosPorDepartamento2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 6)
                {
                    s1c = queryRepository.contagiadosPorSexo1();
                    s2c = queryRepository.contagiadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 7)
                {
                    s1c = queryRepository.recuperadosPorFecha1();
                    s2c = queryRepository.recuperadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 8)
                {
                    s1c = queryRepository.recuperadosPorPais1();
                    s2c = queryRepository.recuperadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 9)
                {
                    s1c = queryRepository.recuperadosPorEdad1();
                    s2c = queryRepository.recuperadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 10)
                {
                    s1c = queryRepository.recuperadosPorSexo1();
                    s2c = queryRepository.recuperadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 11)
                {
                    s1c = queryRepository.muertosPorFecha1();
                    s2c = queryRepository.muertosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 14)
                {
                    s1c = queryRepository.muertosPorPais1();
                    s2c = queryRepository.muertosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 12)
                {
                    s1c = queryRepository.muertosPorEdad1();
                    s2c = queryRepository.muertosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 13)
                {
                    s1c = queryRepository.muertosPorSexo1();
                    s2c = queryRepository.muertosPorSexo2();
                }
                List<QueryXY> datasetc = new ArrayList<>();
                assert s1c != null;
                for (int i = 0; i<s1c.size(); i++
                ) {
                    datasetc.add(new QueryXY(s1c.get(i),s2c.get(i)));
                }
                for (QueryXY xy:datasetc
                ) {
                    System.out.println("X: "+xy.getX()+" Y: "+xy.getY());
                    xAxis.addCategory(xy.getX());
                    nc.addData(xy.getY());
                }

                yAxis = configuration.getyAxis();
                yAxis.setTitle("EjeX");
                yAxis.getLabels().setFormatter("function () { return this.value;}");

                configuration.getTooltip().setValueSuffix(" personas");

                plotOptionsArea = new PlotOptionsArea();
                plotOptionsArea.setStacking(Stacking.NORMAL);
                configuration.setPlotOptions(plotOptionsArea);
                configuration.addSeries(nc);

                return chart;
            case 4:
                //Grafico de Pastel
                chart = new Chart(ChartType.PIE);
                configuration = chart.getConfiguration();
                configuration.setTitle(graphic.getName());
                configuration.setSubTitle(graphic.getDescription());

                Tooltip tooltip = new Tooltip();
                tooltip.setValueDecimals(1);
                configuration.setTooltip(tooltip);

                PlotOptionsPie plotOptions = new PlotOptionsPie();
                plotOptions.setAllowPointSelect(true);
                plotOptions.setCursor(Cursor.POINTER);
                plotOptions.setShowInLegend(true);
                configuration.setPlotOptions(plotOptions);

                DataSeries series = new DataSeries();
                List<String> s1pie = null;
                List<Integer> s2pie = null;
                if(graphic.getIdQuerybi().getIdQuerybi() == 1){
                    return graphicController.generarGrafica(new GraphicConfig(graphic));
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 2)
                {
                    s1pie = queryRepository.numeroAcumuladoDeContagiadosPorFecha1();
                    s2pie = queryRepository.numeroAcumuladoDeContagiadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 3)
                {
                    s1pie = queryRepository.contagiadosPorPais1();
                    s2pie = queryRepository.contagiadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 4)
                {
                    s1pie = queryRepository.contagiadosPorEdad1();
                    s2pie = queryRepository.contagiadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 5)
                {
                    s1pie = queryRepository.contagiadosPorDepartamento1();
                    s2pie = queryRepository.contagiadosPorDepartamento2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 6)
                {
                    s1pie = queryRepository.contagiadosPorSexo1();
                    s2pie = queryRepository.contagiadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 7)
                {
                    s1pie = queryRepository.recuperadosPorFecha1();
                    s2pie = queryRepository.recuperadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 8)
                {
                    s1pie = queryRepository.recuperadosPorPais1();
                    s2pie = queryRepository.recuperadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 9)
                {
                    s1pie = queryRepository.recuperadosPorEdad1();
                    s2pie = queryRepository.recuperadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 10)
                {
                    s1pie = queryRepository.recuperadosPorSexo1();
                    s2pie = queryRepository.recuperadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 11)
                {
                    s1pie = queryRepository.muertosPorFecha1();
                    s2pie = queryRepository.muertosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 14)
                {
                    s1pie = queryRepository.muertosPorPais1();
                    s2pie = queryRepository.muertosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 12)
                {
                    s1pie = queryRepository.muertosPorEdad1();
                    s2pie = queryRepository.muertosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 13)
                {
                    s1pie = queryRepository.muertosPorSexo1();
                    s2pie = queryRepository.muertosPorSexo2();
                }

                List<QueryXY> datasetpie = new ArrayList<>();
                assert s1pie != null;
                for (int i = 0; i<s1pie.size(); i++
                ) {
                    datasetpie.add(new QueryXY(s1pie.get(i),s2pie.get(i)));
                }
                for (QueryXY xy:datasetpie
                ) {
                    System.out.println("X: "+xy.getX()+" Y: "+xy.getY());
                    series.add(new DataSeriesItem(xy.getX(),xy.getY()));
                }
                plotOptionsArea = new PlotOptionsArea();
                plotOptionsArea.setStacking(Stacking.NORMAL);
                configuration.setPlotOptions(plotOptionsArea);
                configuration.setSeries(series);
                chart.setVisibilityTogglingDisabled(true);

                return chart;
            case 5:
                //Grafico de Area
                chart = new Chart(ChartType.AREA);
                configuration = chart.getConfiguration();
                configuration.setTitle(graphic.getName());
                configuration.setSubTitle(graphic.getDescription());
                xAxis = configuration.getxAxis();
                xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
                ListSeries nArea = new ListSeries();
                nArea.setName("Eje X");
                List<String> s1Area = null;
                List<Integer> s2Area = null;
                if(graphic.getIdQuerybi().getIdQuerybi() == 1){
                    return graphicController.generarGrafica(new GraphicConfig(graphic));
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 2)
                {
                    s1Area = queryRepository.numeroAcumuladoDeContagiadosPorFecha1();
                    s2Area = queryRepository.numeroAcumuladoDeContagiadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 3)
                {
                    s1Area = queryRepository.contagiadosPorPais1();
                    s2Area = queryRepository.contagiadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 4)
                {
                    s1Area = queryRepository.contagiadosPorEdad1();
                    s2Area = queryRepository.contagiadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 5)
                {
                    s1Area = queryRepository.contagiadosPorDepartamento1();
                    s2Area = queryRepository.contagiadosPorDepartamento2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 6)
                {
                    s1Area = queryRepository.contagiadosPorSexo1();
                    s2Area = queryRepository.contagiadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 7)
                {
                    s1Area = queryRepository.recuperadosPorFecha1();
                    s2Area = queryRepository.recuperadosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 8)
                {
                    s1Area = queryRepository.recuperadosPorPais1();
                    s2Area = queryRepository.recuperadosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 9)
                {
                    s1Area = queryRepository.recuperadosPorEdad1();
                    s2Area = queryRepository.recuperadosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 10)
                {
                    s1Area = queryRepository.recuperadosPorSexo1();
                    s2Area = queryRepository.recuperadosPorSexo2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 11)
                {
                    s1Area = queryRepository.muertosPorFecha1();
                    s2Area = queryRepository.muertosPorFecha2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 14)
                {
                    s1Area = queryRepository.muertosPorPais1();
                    s2Area = queryRepository.muertosPorPais2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 12)
                {
                    s1Area = queryRepository.muertosPorEdad1();
                    s2Area = queryRepository.muertosPorEdad2();
                }
                else if(graphic.getIdQuerybi().getIdQuerybi() == 13)
                {
                    s1Area = queryRepository.muertosPorSexo1();
                    s2Area = queryRepository.muertosPorSexo2();
                }
                List<QueryXY> datasetArea = new ArrayList<>();
                assert s1Area != null;
                for (int i = 0; i<s1Area.size(); i++
                ) {
                    datasetArea.add(new QueryXY(s1Area.get(i),s2Area.get(i)));
                }
                for (QueryXY xy:datasetArea
                ) {
                    System.out.println("X: "+xy.getX()+" Y: "+xy.getY());
                    xAxis.addCategory(xy.getX());
                    nArea.addData(xy.getY());
                }

                yAxis = configuration.getyAxis();
                yAxis.setTitle("Eje Y");
                yAxis.getLabels().setFormatter("function () { return this.value;}");

                configuration.getTooltip().setValueSuffix(" personas");

                plotOptionsArea = new PlotOptionsArea();
                plotOptionsArea.setStacking(Stacking.NORMAL);
                configuration.setPlotOptions(plotOptionsArea);
                configuration.addSeries(nArea);

                return chart;
            default:
                chart = new Chart();
        }
        return null;
    }

}
